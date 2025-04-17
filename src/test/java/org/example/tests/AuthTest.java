package org.example.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.example.pages.LoginPage;
import org.example.utils.Utils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AuthTest {

    static final String CORRECT_EMAIL = "daria.pinkfloyd@yandex.ru";
    static final String CORRECT_PASSWORD = "9@w3tPUG3WnDXN9";
    static final String INCORRECT_EMAIL = "incorrectd@.ru";
    private static final By LOGIN_LINK = By.xpath("//a[contains(text(),'Войти')]");
    private static final By USER_PROFILE = By.xpath("//a[contains(@class, 'c-top__userpic') and contains(@href, '/users/')]");
    private static final By FORGOT_PASSWORD_LINK = By.xpath("//a[contains(text(),'Забыли?')]");
    private static final By EMAIL_INPUT = By.xpath("//input[@type='text' and @name='Login']");
    private static final By SUBMIT_BUTTON = By.xpath("//button[contains(text(),'Вперёд')]");
    private static final By SUCCESS_MESSAGE = By.xpath("//h1[contains(@class, 'x-title') and text()='Код подтверждения']");

    private List<WebDriver> drivers;

    @BeforeEach
    public void setUp() {
        drivers = Utils.createFreshDrivers();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true));

    }

    @AfterEach
    public void tearDown() {
        drivers.forEach(WebDriver::quit);
    }

    @Test
    @DisplayName("Успешный вход с корректными данными")
    public void loginWithValidCredentials() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(Utils.PAGE);

            driver.findElement(LOGIN_LINK).click();
            new LoginPage(driver).login(CORRECT_EMAIL, CORRECT_PASSWORD);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement profile = wait.until(ExpectedConditions.visibilityOfElementLocated(USER_PROFILE));

            Assertions.assertNotNull(profile, "Профиль пользователя должен быть отображён после входа");
        });
    }

    @Test
    @DisplayName("Восстановление пароля для зарегистрированного пользователя")
    public void passwordRecoveryForRegisteredUser() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(Utils.PAGE);

            driver.findElement(LOGIN_LINK).click();
            driver.findElement(FORGOT_PASSWORD_LINK).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_INPUT));
            emailInput.clear();
            emailInput.sendKeys(CORRECT_EMAIL);

            driver.findElement(SUBMIT_BUTTON).click();

            WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_MESSAGE));

            Assertions.assertTrue(success.isDisplayed(), "Сообщение с кодом должно быть показано");
            Assertions.assertTrue(success.getText().contains("Код подтверждения"));
        });
    }

    @Test
    @DisplayName("Попытка входа с некорректными данными")
    public void loginWithInvalidCredentials() {
        drivers.parallelStream().forEach(driver -> {
            driver.get(Utils.PAGE);

            driver.findElement(LOGIN_LINK).click();
            new LoginPage(driver).login(INCORRECT_EMAIL, CORRECT_PASSWORD);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            By errorMessageLocator = By.xpath("//span[@class='field-validation-error']");

            boolean errorAppeared;
            try {
                WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
                errorAppeared = errorMessage.isDisplayed();
            } catch (TimeoutException e) {
                errorAppeared = false;
            }

            Assertions.assertTrue(errorAppeared, "Должно появиться сообщение об ошибке при вводе некорректных данных");
        });
    }

}

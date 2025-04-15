package org.example.tests;
import org.example.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.example.pages.LoginPage;

import java.time.Duration;
import java.util.List;

public class AuthTest {
    public static final String CORRECT_EMAIL = "daria.pinkfloyd@yandex.ru";
    public static final String CORRECT_PASSWORD = "9@w3tPUG3WnDXN9";
    public static final String INCORRECT_EMAIL = "incorrect@yandex.ru";
    public static final String INCORRECT_PASSWORD = "incorrect";
    public static final String USER_PROFILE_XPATH = "//a[contains(@class, 'c-top__userpic') and contains(@href, '/users/')]";
    public static final String LOGIN_LINK_XPATH = "//a[contains(text(),'Войти')]";

    public static final String FORGOT_PASSWORD_LINK_XPATH = "//a[contains(text(),'Забыли?')]";
    public static final String EMAIL_INPUT_XPATH = "//input[@type='text' and @name='Login']";
    public static final String SUBMIT_BUTTON_XPATH = "//button[contains(text(),'Вперёд')]";
    public static final String SUCCESS_MESSAGE_XPATH = "//h1[contains(@class, 'x-title') and text()='Код подтверждения']";
    @BeforeAll
    public static void initDrivers() {
        Utils.prepareDrivers();
    }

//    @Test
//    public void loginWithValidCredentials() {
//        List<WebDriver> drivers = Utils.getDrivers();
//        drivers.parallelStream().forEach(webDriver -> {
//            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
//
//            webDriver.get(Utils.PAGE);
//            WebElement loginLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_LINK_XPATH)));
//            loginLink.click();
//
//            LoginPage loginPage = new LoginPage(webDriver);
//            loginPage.login(CORRECT_EMAIL, CORRECT_PASSWORD);
//
//            WebElement userProfile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(USER_PROFILE_XPATH)));
//            Assertions.assertNotNull(userProfile, "User profile element should be visible after successful login");
//        });
//        drivers.forEach(WebDriver::quit);
//    }

    @Test
    public void passwordRecoveryForRegisteredUser() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(webDriver -> {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

            webDriver.get(Utils.PAGE);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_LINK_XPATH))).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(FORGOT_PASSWORD_LINK_XPATH))).click();

            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(EMAIL_INPUT_XPATH)));
            emailInput.clear();
            emailInput.sendKeys(CORRECT_EMAIL);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SUBMIT_BUTTON_XPATH))).click();

            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(SUCCESS_MESSAGE_XPATH)));
            Assertions.assertTrue(successMessage.isDisplayed(),
                    "Success message should be displayed after password recovery request");
            Assertions.assertTrue(successMessage.getText()
                            .contains("Код подтверждения"),
                    "Success message should contain expected text");
        });
        drivers.forEach(WebDriver::quit);
    }


}

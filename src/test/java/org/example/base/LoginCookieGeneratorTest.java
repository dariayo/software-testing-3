package org.example.base;


import org.example.pages.LoginPage;
import org.example.utils.Utils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginCookieGeneratorTest {
    static final String CORRECT_EMAIL = "daria.pinkfloyd@yandex.ru";
    static final String CORRECT_PASSWORD = "9@w3tPUG3WnDXN9";

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = Utils.createFreshDrivers().get(0);
    }

    @Test
    @DisplayName("Авторизация и сохранение куков")
    public void generateAndSaveCookies() {
        driver.get(Utils.PAGE);

        driver.findElement(By.xpath("//a[contains(text(),'Войти')]")).click();
        new LoginPage(driver).login(CORRECT_EMAIL, CORRECT_PASSWORD);

        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[contains(@class, 'c-top__userpic') and contains(@href, '/users/')]")));

        Utils.saveCookies(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}


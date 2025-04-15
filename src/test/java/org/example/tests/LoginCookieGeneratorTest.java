package org.example.tests;


import org.example.pages.LoginPage;
import org.example.utils.Utils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginCookieGeneratorTest {

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
        new LoginPage(driver).login(AuthTest.CORRECT_EMAIL, AuthTest.CORRECT_PASSWORD);

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


package org.example.tests;

import org.example.pages.LoginPage;
import org.example.pages.NotificationPage;
import org.example.utils.Utils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.example.tests.AuthTest.CORRECT_EMAIL;
import static org.example.tests.AuthTest.CORRECT_PASSWORD;
import static org.example.tests.SearchTest.LOGIN_LINK_XPATH;
import static org.example.tests.SearchTest.USER_PROFILE_XPATH;

public class NotificationTest {

    private List<WebDriver> drivers;

    @BeforeEach
    public void setUp() {
        drivers = Utils.createFreshDrivers();
        drivers.forEach(driver -> {
            driver.get(Utils.PAGE);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_LINK_XPATH))).click();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(CORRECT_EMAIL, CORRECT_PASSWORD);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(USER_PROFILE_XPATH)));
        });
    }

    @Test
    @DisplayName("Просмотр уведомлений - отображение списка уведомлений")
    public void viewNotifications() {
        drivers.parallelStream().forEach(driver -> {
            NotificationPage notificationsPage = new NotificationPage(driver);
            notificationsPage.openNotifications();

            Assertions.assertTrue(notificationsPage.areNotificationsDisplayed(),
                    "Список уведомлений не отображается");
        });
    }

    @AfterEach
    public void tearDown() {
        drivers.forEach(WebDriver::quit);
    }
}


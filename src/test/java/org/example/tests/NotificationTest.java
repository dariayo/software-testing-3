package org.example.tests;

import org.example.pages.NotificationPage;
import org.example.utils.Utils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class NotificationTest {

    private List<WebDriver> drivers;

    @BeforeEach
    public void setUp() {
        drivers = Utils.createFreshDrivers();
        drivers.forEach(driver -> {
            driver.get(Utils.PAGE);
            Utils.loadCookies(driver);
            driver.navigate().refresh();
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


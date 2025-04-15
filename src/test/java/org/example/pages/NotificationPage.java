package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NotificationPage extends Page {

    public static final String NOTIFICATIONS_ICON_XPATH = "//button[@data-role='notifications-status']";
    public static final String NOTIFICATIONS_LIST_XPATH = "//fancy-scroll[@class='c-ntfy__list']//div[@class='c-notifications']";

    public NotificationPage(WebDriver driver) {
        super(driver);
    }

    public void openNotifications() {
        WebElement notificationsIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(NOTIFICATIONS_ICON_XPATH)));
        notificationsIcon.click();
    }

    public boolean areNotificationsDisplayed() {
        try {
            WebElement notificationsList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(NOTIFICATIONS_LIST_XPATH)));
            return notificationsList.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

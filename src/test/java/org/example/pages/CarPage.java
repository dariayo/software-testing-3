package org.example.pages;

import org.example.base.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CarPage extends Page {

    private static final By ADD_TO_FAVORITES_BUTTON = By.xpath("//bookmark-button");
    private static final By SAVE_BUTTON = By.xpath("//button[contains(text(),'Сохранить')]");
    private static final By FAVORITES_MENU_LINK = By.xpath("//a[contains(@href, '/my/bookmarks/')]");
    private static final By CAR_IN_FAV = By.xpath("//a[contains(@href, '/r/acura/cl/680014/')]");

    public CarPage(WebDriver driver) {
        super(driver);
    }

    public void openCarPage() {
        driver.get("https://www.drive2.ru/r/acura/cl/680014/");
    }

    public void addToFavorites() {
        clickIfClickable(ADD_TO_FAVORITES_BUTTON);
        WebElement saveBtn = waitForClickable(SAVE_BUTTON);
        scrollToElement(saveBtn);

        safeClick(saveBtn);
    }

    public void openFavorites() {
        WebElement favLink = waitForClickable(FAVORITES_MENU_LINK);
        safeClick(favLink);
    }

    public boolean isCarInFavorites() {
        return isVisible(CAR_IN_FAV);
    }
}

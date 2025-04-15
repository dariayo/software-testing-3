package org.example.pages;

import org.example.base.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FavoritesPage extends Page {

    private static final By CAR_DOTS_MENU = By.xpath("//button[@data-action='bookmarks.edit' and @data-id='680014']");
    private static final By DELETE_BUTTON = By.xpath("//button[contains(text(),'Удалить') and @type='button']");
    private static final By CONFIRM_DELETE_BUTTON = By.xpath("//button[contains(text(),'Удалить') and contains(@class, 'c-button--primary')]");

    public FavoritesPage(WebDriver driver) {
        super(driver);
    }

    public void removeCarFromFavorites() {
        WebElement dots = waitForClickable(CAR_DOTS_MENU);
        scrollToElement(dots);
        safeClick(dots);

        WebElement delete = waitForClickable(DELETE_BUTTON);
        safeClick(delete);

        WebElement confirm = waitForClickable(CONFIRM_DELETE_BUTTON);
        safeClick(confirm);
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

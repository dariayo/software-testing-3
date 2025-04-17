package org.example.pages;

import org.example.base.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarFilterPage extends Page {

    private static final By CARS_MENU_LINK = By.xpath("//a[contains(@href, '/cars/')]");
    private static final By ACURA_LINK = By.xpath("//a[contains(@href, '/cars/audi/')]");
    private static final By CL_LINK = By.xpath("//a[contains(@href, '/cars/audi/100/')]");
    private static final By CL_GENERATION_LINK = By.xpath("//a[contains(@href, '/r/audi/100/483608920556831034/')]");

    public CarFilterPage(WebDriver driver) {
        super(driver);
    }

    public void openCarsSection() {
        clickIfClickable(CARS_MENU_LINK);
    }

    public void selectAcura() {
        clickIfClickable(ACURA_LINK);
    }

    public void selectCLModel() {
        clickIfClickable(CL_LINK);
    }

    public void selectCLGeneration() {
        clickIfClickable(CL_GENERATION_LINK);
    }

    public boolean isOnCLGenerationPage() {
        return driver.getCurrentUrl().contains("/r/audi/100/");
    }
}

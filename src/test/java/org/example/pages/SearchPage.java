package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends Page {

    public static final String SEARCH_INPUT_XPATH = "//input[@type='search']";
    public static final String SEARCH_BUTTON_XPATH = "//button[contains(., 'Найти')]";
    public static final String SEARCH_RESULTS_XPATH = "//div[@class='o-f' and @data-slot='site-search.results']";
    public static final String SEARCH_RESULTS_TITLE_XPATH = "//input[@type='search' and @name='text']";

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void searchByBrand(String brand) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SEARCH_INPUT_XPATH)));
        searchInput.clear();
        searchInput.sendKeys(brand);

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SEARCH_BUTTON_XPATH)));
        searchButton.click();
    }

    public boolean areResultsDisplayed() {
        try {
            WebElement results = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SEARCH_RESULTS_XPATH)));
            return results.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isResultsTitleContains(String text) {
        try {
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SEARCH_RESULTS_TITLE_XPATH)));
            return searchInput.getAttribute("value").contains(text);
        } catch (Exception e) {
            return false;
        }
    }
}

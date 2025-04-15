package org.example.tests;

import org.example.pages.LoginPage;
import org.example.pages.SearchPage;
import org.example.utils.Utils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchTest {
    private List<WebDriver> drivers;
    private WebDriverWait wait;

    @BeforeAll
    public static void initDrivers() {
        Utils.prepareDrivers();
    }

    @BeforeEach
    public void setUp() {
        drivers = Utils.getDrivers();
        drivers.forEach(driver -> {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            driver.get(Utils.PAGE);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AuthTest.LOGIN_LINK_XPATH))).click();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(AuthTest.CORRECT_EMAIL, AuthTest.CORRECT_PASSWORD);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AuthTest.USER_PROFILE_XPATH)));
        });
    }

    @Test
    public void searchCarByBrand() {
        drivers.parallelStream().forEach(driver -> {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            SearchPage searchPage = new SearchPage(driver);
            searchPage.searchByBrand("Toyota");

            Assertions.assertTrue(searchPage.areResultsDisplayed());
        });
    }

    @AfterEach
    public void tearDown() {
        drivers.forEach(WebDriver::quit);
    }
}

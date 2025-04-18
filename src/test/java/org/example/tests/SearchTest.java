package org.example.tests;

import org.example.pages.SearchPage;
import org.example.utils.Utils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class SearchTest {
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
    @DisplayName("Поиск автомобиля по марке должен возвращать результаты")
    public void searchCarByBrand() {
        drivers.parallelStream().forEach(driver -> {
            SearchPage searchPage = new SearchPage(driver);
            searchPage.searchByBrand("Toyota");

            Assertions.assertTrue(searchPage.areResultsDisplayed(),
                    "Результаты поиска не отображаются");
            Assertions.assertTrue(searchPage.isResultsTitleContains("Toyota"),
                    "Заголовок результатов не содержит 'Toyota'");
        });
    }

    @AfterEach
    public void tearDown() {
        drivers.forEach(WebDriver::quit);
    }
}

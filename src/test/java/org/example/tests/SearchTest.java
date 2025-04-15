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

import static org.example.tests.AuthTest.CORRECT_EMAIL;
import static org.example.tests.AuthTest.CORRECT_PASSWORD;

public class SearchTest {
    public static final String LOGIN_LINK_XPATH = "//a[contains(text(),'Войти')]";
    public static final String USER_PROFILE_XPATH = "//a[contains(@class, 'c-top__userpic') and contains(@href, '/users/')]";

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
    @DisplayName("Поиск автомобиля по бренду должен возвращать результаты")
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

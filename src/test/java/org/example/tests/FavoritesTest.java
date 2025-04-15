package org.example.tests;

import org.example.pages.CarPage;
import org.example.pages.FavoritesPage;
import org.example.pages.LoginPage;
import org.example.utils.Utils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.example.tests.AuthTest.*;

public class FavoritesTest {

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
    @DisplayName("Добавление и удаление автомобиля из избранного")
    public void addAndRemoveCarFromFavorites() {
        drivers.parallelStream().forEach(driver -> {
            CarPage carPage = new CarPage(driver);
            FavoritesPage favoritesPage = new FavoritesPage(driver);

            carPage.openCarPage();
            carPage.addToFavorites();

            carPage.openFavorites();
            Assertions.assertTrue(carPage.isCarInFavorites(), "Машина должна быть в избранном");

            favoritesPage.removeCarFromFavorites();
        });
    }

    @AfterEach
    public void tearDown() {
        drivers.forEach(WebDriver::quit);
    }
}

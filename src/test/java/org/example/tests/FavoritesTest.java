package org.example.tests;

import org.example.pages.CarPage;
import org.example.pages.FavoritesPage;
import org.example.utils.Utils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import java.util.List;


public class FavoritesTest {
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
            Assertions.assertTrue(favoritesPage.isCarRemoved(), "Машина должна быть удалена из избранного");
        });
    }

    @AfterEach
    public void tearDown() {
        drivers.forEach(WebDriver::quit);
    }
}

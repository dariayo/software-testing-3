package org.example.tests;

import org.example.pages.CarFilterPage;
import org.example.utils.Utils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CarFilterTest {

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
    @DisplayName("Переход к поколению машины через меню Машины -> Acura -> CL -> CL Generation")
    public void navigateToCarGeneration() {
        drivers.parallelStream().forEach(driver -> {
            CarFilterPage carsPage = new CarFilterPage(driver);

            carsPage.openCarsSection();
            carsPage.selectAcura();
            carsPage.selectCLModel();
            carsPage.selectCLGeneration();

            Assertions.assertTrue(carsPage.isOnCLGenerationPage(), "Должен быть открыт раздел поколения Acura CL");
        });
    }

    @AfterEach
    public void tearDown() {
        drivers.forEach(WebDriver::quit);
    }
}

package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    public static final String WEBDRIVER_FIREFOX_DRIVER = "webdriver.firefox.driver";
    public static final String CHROME_DRIVER_PATH = "src/main/resources/chromedriver";
    public static final String FIREFOX_DRIVER_PATH = "src/test/resources/geckodriver.exe";
    public static final String PAGE = "https://www.drive2.ru/";

    public static List<WebDriver> getDrivers() {
        List<WebDriver> drivers = new ArrayList<>();
        try {
            List<String> properties = Files.readAllLines(Paths.get("start.properties"));
            for (String property : properties) {
                switch (property.toLowerCase().split("=")[1]) {
                    case "chrome":
                        drivers.add(getChromeDriver()); return drivers;
                    case "firefox":
                        drivers.add(getFirefoxDriver()); return drivers;
                    case "both":
                        drivers.add(getChromeDriver());
                        drivers.add(getFirefoxDriver());
                        return drivers;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Web driver is not specified");
    }

    public static void prepareDrivers() {
        System.setProperty(Utils.WEBDRIVER_CHROME_DRIVER, Utils.CHROME_DRIVER_PATH);
        System.setProperty(Utils.WEBDRIVER_FIREFOX_DRIVER, Utils.FIREFOX_DRIVER_PATH);
    }

    private static ChromeDriver getChromeDriver() {
        if (!System.getProperties().containsKey(WEBDRIVER_CHROME_DRIVER)) {
            throw new RuntimeException("Chrome driver not set properly");
        }
        return new ChromeDriver();
    }

    private static FirefoxDriver getFirefoxDriver() {
        if (!System.getProperties().containsKey(WEBDRIVER_FIREFOX_DRIVER)) {
            throw new RuntimeException("Firefox driver not set properly");
        }
        return new FirefoxDriver();
    }
}

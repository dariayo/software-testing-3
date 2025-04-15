package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static final String PAGE = "https://www.drive2.ru/";

    public static List<WebDriver> createFreshDrivers() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");

        List<WebDriver> drivers = new ArrayList<>();
        drivers.add(new ChromeDriver());

        return drivers;
    }
}

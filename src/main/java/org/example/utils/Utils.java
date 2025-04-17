package org.example.utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.*;

public class Utils {

    public static final String PAGE = "https://www.drive2.ru/";
    private static final String COOKIE_FILE = "src/main/resources/cookies.data";

    public static List<WebDriver> createFreshDrivers() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");

        List<WebDriver> drivers = new ArrayList<>();
        drivers.add(new ChromeDriver());

        return drivers;
    }

    public static void saveCookies(WebDriver driver) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COOKIE_FILE))) {
            for (Cookie cookie : driver.manage().getCookies()) {
                String expiry = cookie.getExpiry() != null ? String.valueOf(cookie.getExpiry().getTime()) : "null";
                writer.write(cookie.getName() + ";" +
                        cookie.getValue() + ";" +
                        cookie.getDomain() + ";" +
                        cookie.getPath() + ";" +
                        expiry + ";" +
                        cookie.isSecure());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить cookies", e);
        }
    }

    public static void loadCookies(WebDriver driver) {
        File file = new File(COOKIE_FILE);
        if (!file.exists()) {
            throw new RuntimeException("Файл cookies не найден. Сначала выполните логин и сохраните куки.");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length != 6) continue;

                String name = parts[0];
                String value = parts[1];
                String domain = parts[2];
                String path = parts[3];
                String expiry = parts[4];
                boolean isSecure = Boolean.parseBoolean(parts[5]);

                Date expiryDate = null;
                if (!expiry.equals("null")) {
                    expiryDate = new Date(Long.parseLong(expiry));
                }

                Cookie cookie = new Cookie.Builder(name, value)
                        .domain(domain)
                        .path(path)
                        .expiresOn(expiryDate)
                        .isSecure(isSecure)
                        .build();

                driver.manage().addCookie(cookie);
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить cookies", e);
        }
    }

}

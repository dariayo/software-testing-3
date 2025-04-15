package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends Page {

    public static final String EMAIL_FIELD_XPATH = "//input[@type='email']";
    public static final String PASSWORD_FIELD_XPATH = "//input[@type='password']";
    public static final String LOGIN_BUTTON_XPATH = "//button[contains(., 'Войти')]";


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement emailFieldInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMAIL_FIELD_XPATH)));
        emailFieldInput.clear();
        emailFieldInput.click();
        emailFieldInput.sendKeys(email);

        WebElement passwordFieldInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PASSWORD_FIELD_XPATH)));
        passwordFieldInput.clear();
        passwordFieldInput.click();
        passwordFieldInput.sendKeys(password);

        WebElement loginBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_BUTTON_XPATH)));
        loginBtn.click();
    }
}

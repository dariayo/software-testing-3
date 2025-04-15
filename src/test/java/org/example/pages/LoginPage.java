package org.example.pages;

import org.example.base.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends Page {

    private static final By EMAIL_FIELD = By.xpath("//input[@type='email']");
    private static final By PASSWORD_FIELD = By.xpath("//input[@type='password']");
    private static final By LOGIN_BUTTON = By.xpath("//button[contains(., 'Войти')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        WebElement emailFieldInput = waitForVisible(EMAIL_FIELD);
        emailFieldInput.clear();
        emailFieldInput.sendKeys(email);

        WebElement passwordFieldInput = waitForVisible(PASSWORD_FIELD);
        passwordFieldInput.clear();
        passwordFieldInput.sendKeys(password);

        waitForVisible(LOGIN_BUTTON).click();
    }
}

package org.example.tests;

import org.example.pages.BlogPage;
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

public class CreatePostTest {
    public static final String LOGIN_LINK_XPATH = "//a[contains(text(),'Войти')]";
    public static final String USER_PROFILE_XPATH = "//a[contains(@class, 'c-top__userpic') and contains(@href, '/users/')]";


    private List<WebDriver> drivers;

    @BeforeEach
    public void setUp() {
        drivers = Utils.createFreshDrivers();

        drivers.forEach(driver -> {
            driver.get(Utils.PAGE);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_LINK_XPATH))).click();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(CORRECT_EMAIL, CORRECT_PASSWORD);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(USER_PROFILE_XPATH)));
        });
    }

    @Test
    @DisplayName("Создание нового поста в блоге")
    public void createNewBlogPost() {
        drivers.parallelStream().forEach(driver -> {
            BlogPage blogPage = new BlogPage(driver);

            blogPage.goToPersonalBlog();
            blogPage.clickCreateNewPost();

            String postTitle = "Тестовый пост " + System.currentTimeMillis();
            String postContent = "Это автоматически созданный тестовый пост";

            blogPage.enterPostTitle(postTitle);
            blogPage.enterPostContent(postContent);
            blogPage.publishPost();

            Assertions.assertTrue(blogPage.isPostVisible(postTitle),
                    "Новый пост должен отображаться в блоге");
        });
    }

    @AfterEach
    public void tearDown() {
        drivers.forEach(WebDriver::quit);
    }
}

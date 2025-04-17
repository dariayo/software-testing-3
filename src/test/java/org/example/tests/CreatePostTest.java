package org.example.tests;

import org.example.pages.BlogPage;
import org.example.utils.Utils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CreatePostTest {

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

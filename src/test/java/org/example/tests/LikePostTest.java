package org.example.tests;

import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.example.pages.BlogLikesPage;
import org.example.utils.Utils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.*;

public class LikePostTest {

    private final BlogLikesPage blogPage = new BlogLikesPage();
    private static final String BLOG_URL = "https://www.drive2.ru/users/daria1519/blog/";
    private static final String POST_SELECTOR = "//div[contains(@class, 'c-post')][1]";

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;

        open("https://www.drive2.ru/");
        WebDriver driver = WebDriverRunner.getWebDriver();
        Utils.loadCookies(driver);
        driver.navigate().refresh();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true));
    }

    @Test
    @DisplayName("Проверка лайка поста в блоге")
    void testLikePost() {
        blogPage.openBlog(BLOG_URL);
        int initialLikes = blogPage.getLikeCount(POST_SELECTOR);
        blogPage.likePost(POST_SELECTOR);
        blogPage.verifyLikeCountIncreased(POST_SELECTOR, initialLikes);
        blogPage.unlikePost(POST_SELECTOR);
        blogPage.verifyLikeCountDecreased(POST_SELECTOR, initialLikes);
    }
}

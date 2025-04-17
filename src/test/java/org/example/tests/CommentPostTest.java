package org.example.tests;

import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.example.pages.CommentPage;
import org.example.utils.Utils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.*;

public class CommentPostTest {

    private final CommentPage commentPage = new CommentPage();
    private static final String POST_URL = "https://www.drive2.ru/b/702254779199407914/";
    private static final String COMMENT_TEXT = "Тестовый комментарий " + System.currentTimeMillis();

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
    @DisplayName("Добавление комментария к посту")
    void testAddComment() {
        commentPage.openPost(POST_URL);
        commentPage.writeComment(COMMENT_TEXT);
        commentPage.submitComment();
        commentPage.checkCommentVisible(COMMENT_TEXT);
    }
}

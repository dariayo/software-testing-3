package org.example.pages;

import org.example.base.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BlogPage extends Page {

    private static final By PERSONAL_BLOG_LINK = By.xpath("//a[contains(@href, '/blog/')]");
    private static final By CREATE_POST_BUTTON = By.xpath("//a[contains(text(), 'Написать в блог')]");
    private static final By POST_TITLE_INPUT = By.xpath("//input[@placeholder='Заголовок записи']");
    private static final By POST_CONTENT_INPUT = By.xpath("//textarea[@id='Text' and @name='Text']");
    private static final By PUBLISH_BUTTON = By.xpath("//button[@type='submit' and contains(text(), 'Опубликовать')]");

    public BlogPage(WebDriver driver) {
        super(driver);
    }

    public void goToPersonalBlog() {
        clickIfClickable(PERSONAL_BLOG_LINK);
    }

    public void clickCreateNewPost() {
        clickIfClickable(CREATE_POST_BUTTON);
    }

    public void enterPostTitle(String title) {
        inputText(POST_TITLE_INPUT, title);
    }

    public void enterPostContent(String content) {
        inputText(POST_CONTENT_INPUT, content);
    }

    public void publishPost() {
        WebElement button = waitForClickable(PUBLISH_BUTTON);
        scrollToElement(button);
        safeClick(button);
    }

    public boolean isPostVisible(String postTitle) {
        try {
            By postTitleLocator = By.xpath(String.format("//h1[contains(text(), '%s')]", postTitle));
            return isVisible(postTitleLocator);
        } catch (Exception e) {
            return false;
        }
    }
}

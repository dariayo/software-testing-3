package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class BlogLikesPage {

    public void openBlog(String blogUrl) {
        open(blogUrl);
    }

    public int getLikeCount(String postSelector) {
        SelenideElement likeButton = $x(postSelector + "//like-button");
        return Integer.parseInt(likeButton.getAttribute("count"));
    }

    public void likePost(String postSelector) {
        SelenideElement likeButton = $x(postSelector + "//like-button");
        likeButton.shouldBe(visible, enabled).click();
    }

    public void unlikePost(String postSelector) {
        SelenideElement likeButton = $x(postSelector + "//like-button");
        likeButton.shouldBe(visible, enabled).click();
    }

    public void verifyLikeCountIncreased(String postSelector, int initialCount) {
        $x(postSelector + "//like-button")
                .shouldHave(attribute("count", String.valueOf(initialCount + 1)));
    }

    public void verifyLikeCountDecreased(String postSelector, int initialCount) {
        $x(postSelector + "//like-button")
                .shouldHave(attribute("count", String.valueOf(initialCount)));
    }
}

package org.example.pages;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class CommentPage {

    public void openPost(String postUrl) {
        open(postUrl);
    }

    public void writeComment(String text) {
        $x("//textarea[@placeholder='Написать комментарий']").shouldBe(visible).setValue(text);
    }

    public void submitComment() {
        $x("//button[contains(text(),'Отправить')]").shouldBe(enabled).click();
    }

    public void checkCommentVisible(String text) {
        $("#comments").shouldHave(text(text));
    }
}

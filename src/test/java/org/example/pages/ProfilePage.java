package org.example.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class ProfilePage {

    public void openProfileEdit() {
        $x("//a[contains(@class,'c-top__userpic')]").shouldBe(visible).click();
        $x("//a[text()='Редактировать профиль']").shouldBe(visible).click();
    }

    public void changeFirstName(String newName) {
        $("#firstName").shouldBe(visible).clear();
        $("#firstName").setValue(newName);
    }

    public void saveProfile() {
        SelenideElement saveButton = $x("//button[contains(text(),'Сохранить')]");
        executeJavaScript("arguments[0].scrollIntoView(true);", saveButton);
        executeJavaScript("arguments[0].click();", saveButton);
    }

    public void checkProfileUpdated() {
        $x("//h3[contains(text(),'Ваш профиль обновлён')]").shouldBe(visible);
    }
}

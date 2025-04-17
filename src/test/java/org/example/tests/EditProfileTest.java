package org.example.tests;

import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.example.pages.ProfilePage;
import org.example.utils.Utils;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;

public class EditProfileTest {

    private final ProfilePage profilePage = new ProfilePage();

    private static final String NEW_NAME = "ТестовоеИмя" + System.currentTimeMillis();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;

        open("https://www.drive2.ru/");
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
        Utils.loadCookies(WebDriverRunner.getWebDriver());
        refresh();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true));
    }

    @Test
    @DisplayName("Изменение имени в профиле")
    void testEditProfileName() {
        profilePage.openProfileEdit();
        profilePage.changeFirstName(NEW_NAME);
        profilePage.saveProfile();
        profilePage.checkProfileUpdated();
    }
}

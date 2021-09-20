package cloud.autotests.tests;

import cloud.autotests.helpers.CustomTestWatcher;
import cloud.autotests.helpers.WithLogin;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

@Feature("Profile tests")
@ExtendWith(CustomTestWatcher.class)
public class ProfileTests extends TestBase {

    @WithLogin
    @Test
    void profilePageShouldContainValidDataTest() {
        open("");
        $(".Avatar").click();
        $$(".UserMenu__menu .Menu__item")
                .find(text("Your profile"))
                .click();
        $$(".PaneSection").find(text("Username")).should(text("testuser"));
        $$(".PaneSection").find(text("Full name")).should(text("Test User"));
    }


}

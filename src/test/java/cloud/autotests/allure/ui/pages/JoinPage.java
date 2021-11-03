package cloud.autotests.allure.ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class JoinPage {

    private static final String FORM_TITLE = "Sign up";

    SelenideElement formTitle = $(".SignUpLayout__title");

    public JoinPage checkFormTitle() {
        formTitle.shouldHave(text(FORM_TITLE));
        return this;
    }
}

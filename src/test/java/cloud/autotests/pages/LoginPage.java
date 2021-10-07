package cloud.autotests.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private SelenideElement usernameInput = $("input[type=text]");
    private SelenideElement passwordInput = $("input[type=password]");
    private SelenideElement continueButton = $("button[type=submit]");

    @Step("Open login page")
    public LoginPage openLoginPage(String url) {
        open(url);
        return this;
    }

    @Step("Set 'Username'")
    public LoginPage setUsernameInput(String value) {
        usernameInput.val(value);
        return this;
    }

    @Step("Set 'Password'")
    public LoginPage setPasswordInput(String value) {
        passwordInput.val(value);
        return this;
    }

    @Step("Click 'Continue' button")
    public LoginPage clickContinueButton() {
        continueButton.click();
        return this;
    }
}

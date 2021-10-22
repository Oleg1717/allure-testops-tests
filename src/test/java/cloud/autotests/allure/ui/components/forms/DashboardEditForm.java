package cloud.autotests.allure.ui.components.forms;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardEditForm {

    private SelenideElement form = $(".ReactModal__Content");
    private SelenideElement formHeader = form.$(".Modal__header");
    private SelenideElement formContent = form.$(".Modal__content");
    private SelenideElement formControls = form.$(".Form__controls");

    private SelenideElement formName = formHeader.$(".Modal__name");
    private SelenideElement closeButton = formHeader.$("button[type=button]");

    private SelenideElement nameInput = formContent.$("input[name=name]");
    private SelenideElement nameErrorMessage = formContent.$(".Form__error");

    private SelenideElement cancelButton = formControls.$("button[type=button]");
    private SelenideElement submitButton = formControls.$("button[type=submit]");

    @Step("Set value '{value}' in input field 'Name'")
    public DashboardEditForm setNameInput(String value) {
        //nameInput.clear();
        nameInput.val(value);
        return this;
    }

    @Step("Check that form name is '{name}'")
    public void checkThatFormNameIs(String name) {
        formName.shouldHave(text(name));
    }

    @Step("Check that 'Name' error message is '{errorMessage}'")
    public DashboardEditForm checkThatNameErrorMessageIs(String errorMessage) {
        nameErrorMessage.shouldHave(text(errorMessage));
        return this;
    }

    @Step("Check that form is closed")
    public DashboardEditForm checkThatFormIsClosed() {
        form.shouldNot(visible);
        return this;
    }

    @Step("Click close button on form")
    public DashboardEditForm clickCloseButton() {
        closeButton.click();
        return this;
    }

    @Step("Click submit button on form")
    public DashboardEditForm clickSubmitButton() {
        submitButton.click();
        return this;
    }

    @Step("Click cancel button on form")
    public DashboardEditForm clickCancelButton() {
        cancelButton.click();
        return this;
    }

}
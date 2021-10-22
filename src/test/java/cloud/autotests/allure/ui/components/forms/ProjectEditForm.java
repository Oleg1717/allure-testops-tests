package cloud.autotests.allure.ui.components.forms;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ProjectEditForm {

    private SelenideElement form = $(".ReactModal__Content");
    private SelenideElement formHeader = form.$(".Modal__header");
    private SelenideElement formContent = form.$(".Modal__content");
    private SelenideElement formControls = form.$(".Form__controls");

    private SelenideElement formName = formHeader.$(".Modal__name");
    private SelenideElement closeButton = formHeader.$("button[type=button]");

    private SelenideElement formAlertMessage = formContent.$(".Alert");
    private SelenideElement nameInput = formContent.$("input[name=name]");
    private SelenideElement nameErrorMessage = formContent.$(".Form__error");
    private SelenideElement abbreviationInput = formContent.$("input[name=abbr]");
    private SelenideElement descriptionWriteTextArea = formContent.$(".MarkdownTextarea__edit");
    private SelenideElement publicCheckbox = formContent.$(".Checkbox");

    private SelenideElement cancelButton = formControls.$("button[type=button]");
    private SelenideElement submitButton = formControls.$("button[type=submit]");

    @Step("Set value '{value}' in input field 'Name'")
    public ProjectEditForm setNameInput(String value) {
        nameInput.val(value);
        return this;
    }

    @Step("Set value '{value}' in input field 'Abbreviation'")
    public ProjectEditForm setAbbreviationInput(String value) {
        abbreviationInput.val(value);
        return this;
    }

    @Step("Fill write textarea of form description field")
    public ProjectEditForm fillContentWriteTextArea(String textarea) {
        descriptionWriteTextArea.val(textarea);
        return this;
    }

    @Step("Make new project public")
    public ProjectEditForm clickPublicCheckbox() {
        publicCheckbox.click();
        return this;
    }

    @Step("Check that 'Name' error message is '{errorMessage}'")
    public ProjectEditForm checkThatNameErrorMessageIs(String errorMessage) {
        nameErrorMessage.shouldHave(text(errorMessage));
        return this;
    }

    @Step("Check that form alert message is '{alertMessage}'")
    public ProjectEditForm checkThatFormAlertIs(String alertMessage) {
        formAlertMessage.shouldHave(text(alertMessage));
        return this;
    }

    //region Form button steps
    @Step("Click close button on form")
    public ProjectEditForm clickCloseButton() {
        closeButton.click();
        return this;
    }

    @Step("Click submit button on form")
    public ProjectEditForm clickSubmitButton() {
        submitButton.click();
        return this;
    }

    @Step("Click cancel button on form")
    public ProjectEditForm clickCancelButton() {
        cancelButton.click();
        return this;
    }
    //endregion
}

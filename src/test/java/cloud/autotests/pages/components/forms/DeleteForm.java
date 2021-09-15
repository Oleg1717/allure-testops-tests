package cloud.autotests.pages.components.forms;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class DeleteForm {

    private SelenideElement form = $(".ReactModal__Content");
    private SelenideElement header = form.$(".Modal__header");
    private SelenideElement content = form.$(".Modal__content");

    private SelenideElement closeButton = header.$("button[type=button]");
    private SelenideElement deleteButton = content.$("button[type=button]");

    @Step("Click close button on form")
    public void clickCloseButton() {
        closeButton.click();
    }

    @Step("Click delete button on form")
    public void clickDeleteButton() {
        deleteButton.click();
    }
}
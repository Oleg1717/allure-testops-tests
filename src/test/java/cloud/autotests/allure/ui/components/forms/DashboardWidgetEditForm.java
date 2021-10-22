package cloud.autotests.allure.ui.components.forms;

import cloud.autotests.allure.ui.data.dashboards.FormGroupByItem;
import cloud.autotests.allure.ui.data.dashboards.FormLaunchAnalyticMetricItem;
import cloud.autotests.allure.ui.data.dashboards.FormTopTestCasesMetricItem;
import cloud.autotests.allure.ui.data.dashboards.FormTreeItem;
import cloud.autotests.allure.ui.data.dashboards.FormTypeItem;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class DashboardWidgetEditForm {


    private SelenideElement form = $(".ReactModal__Content");
    private SelenideElement formHeader = form.$(".Modal__header");
    private SelenideElement formContent = form.$(".Modal__content");
    private SelenideElement formControls = form.$(".Form__controls");

    private SelenideElement formName = formHeader.$(".Modal__name");
    private SelenideElement closeButton = formHeader.$("button[type=button]");

    private ElementsCollection contentFields = formContent.$$(".FormLabel__name");
    private ElementsCollection dropDawnList = $(".css-11unzgr").$$("div");

    private SelenideElement nameInput = getFieldByLabel("Name").$("input[name=name]");
    private SelenideElement nameErrorMessage = getFieldByLabel("Name").$(".Form__error");
    private SelenideElement contentWriteTextArea = getFieldByLabel("Content").$(".MarkdownTextarea__edit");
    private SelenideElement testCasesQueryInput = getFieldByLabel("Test cases query").$("input[name=query]");
    private SelenideElement testCasesQueryStatus = getFieldByLabel("Test cases query")
            .$(".InputQuery__status");
    private SelenideElement testCasesQueryCount = getFieldByLabel("Test cases query")
            .$(".InputQuery__count");
    private SelenideElement launchersQueryInput = getFieldByLabel("Launchers query").$("input[name=query]");
    private SelenideElement launchersQueryStatus = getFieldByLabel("Launchers query")
            .$(".InputQuery__status");
    private SelenideElement launchersQueryCount = getFieldByLabel("Launchers query")
            .$(".InputQuery__count");
    private SelenideElement typeSelect = getFieldByLabel("Type").$(".css-2b097c-container");
    private SelenideElement metricSelect = getFieldByLabel("Metric").$(".Select");
    private SelenideElement groupBySelect = getFieldByLabel("Group By").$(".Select");
    private SelenideElement treeSelect = getFieldByLabel("Tree").$(".Select");

    private SelenideElement cancelButton = formControls.$("button[type=button]");
    private SelenideElement submitButton = formControls.$("button[type=submit]");

    //region Get methods
    private SelenideElement getFieldByLabel(String fieldLabel) {
        return contentFields.find(text(fieldLabel)).parent();
    }

    private SelenideElement getSelectedItem(String listItemName) {
        return dropDawnList.find(text(listItemName));
    }
    //endregion

    //region Entering data steps
    @Step("Set value '{value}' in input field 'Name'")
    public DashboardWidgetEditForm setNameInput(String value) {
        nameInput.val(value);
        return this;
    }

    @Step("Fill write textarea of form content field")
    public DashboardWidgetEditForm fillContentWriteTextArea(String textarea) {
        contentWriteTextArea.val(textarea);
        return this;
    }

    @Step("Set value '{value}' in input field 'Test cases query'")
    public DashboardWidgetEditForm setTestCasesQueryInput(String value) {
        testCasesQueryInput.val(value);
        return this;
    }

    @Step("Set value '{value}' in input field 'Launchers query'")
    public DashboardWidgetEditForm setLaunchersQueryValue(String value) {
        launchersQueryInput.val(value);
        return this;
    }
    //endregion

    //region Check data steps
    @Step("Check that 'Name' error message is '{errorMessage}'")
    public DashboardWidgetEditForm checkThatNameErrorMessageIs(String errorMessage) {
        nameErrorMessage.shouldHave(text(errorMessage));
        return this;
    }

    @Step("Check that 'Test Cases Query' status is '{status}'")
    public DashboardWidgetEditForm checkThatTestCasesQueryStatusIs(String status) {
        testCasesQueryStatus.shouldHave(text(status));
        return this;
    }

    @Step("Check that 'Test Cases Query' count is '{count}'")
    public DashboardWidgetEditForm checkThatTestCasesQueryCountIs(String count) {
        testCasesQueryCount.shouldHave(text(count));
        return this;
    }

    @Step("Check that 'Launchers Query' status is '{status}'")
    public DashboardWidgetEditForm checkThatLaunchersQueryStatusIs(String status) {
        launchersQueryStatus.shouldHave(text(status));
        return this;
    }

    @Step("Check that 'Launchers Query' count is '{count}'")
    public DashboardWidgetEditForm checkThatLaunchersQueryCountIs(String count) {
        launchersQueryCount.shouldHave(text(count));
        return this;
    }
    //endregion

    //region Select item steps
    @Step("Select item '{itemName}' from drop-down list 'Type'")
    public DashboardWidgetEditForm selectTypeItem(FormTypeItem itemName) {
        typeSelect.click();
        sleep(1000);
        typeSelect.click();
        getSelectedItem(itemName.getDisplayedName()).click();
        return this;
    }

    @Step("Select item '{itemName}' from drop-down list 'Metric'")
    public DashboardWidgetEditForm selectLaunchAnalyticMetricItem(FormLaunchAnalyticMetricItem itemName) {
        metricSelect.click();
        getSelectedItem(itemName.getDisplayedName()).click();
        return this;
    }

    @Step("Select item '{itemName}' from drop-down list 'Metric'")
    public DashboardWidgetEditForm selectTopTestCasesMetricItem(FormTopTestCasesMetricItem itemName) {
        metricSelect.click();
        getSelectedItem(itemName.getDisplayedName()).click();
        return this;
    }

    @Step("Select item '{itemName}' from drop-down list 'Group By'")
    public DashboardWidgetEditForm selectGroupByItem(FormGroupByItem itemName) {
        groupBySelect.click();
        getSelectedItem(itemName.getDisplayedName()).click();
        return this;
    }

    @Step("Select item '{itemName}' from drop-down list 'Tree'")
    public DashboardWidgetEditForm selectTreeItem(FormTreeItem itemName) {
        treeSelect.click();
        getSelectedItem(itemName.getDisplayedName()).click();
        return this;
    }
    //endregion

    //region Form button steps
    @Step("Click close button on form")
    public DashboardWidgetEditForm clickCloseButton() {
        closeButton.click();
        return this;
    }

    @Step("Click submit button on form")
    public DashboardWidgetEditForm clickSubmitButton() {
        submitButton.click();
        return this;
    }

    @Step("Click cancel button on form")
    public DashboardWidgetEditForm clickCancelButton() {
        cancelButton.click();
        return this;
    }
    //endregion
}

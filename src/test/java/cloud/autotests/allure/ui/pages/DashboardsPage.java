package cloud.autotests.allure.ui.pages;

import cloud.autotests.allure.ui.data.dashboards.DashboardActionItem;
import cloud.autotests.allure.ui.data.dashboards.WidgetActionItem;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class DashboardsPage {

    private final ElementsCollection widgetsList = $(".ProjectDashboards__content").$$(".react-grid-item");
    private final ElementsCollection menuItems = $$(".Menu__item");

    private final SelenideElement newDashboardButton = $(".ProjectDashboards__title").$("button[type=button]");
    private final SelenideElement dashboardsTabs = $(".ProjectDashboards__tabs");
    private final SelenideElement dashboardActionsButton = $(".ProjectDashboards__navigation").$("button[type=button]");

    private final SelenideElement fullScreenControls = $(".Dashboard__fullscreen-controls");
    private final SelenideElement addWidgetButton = $(".Dashboard__empty .Button");
    private final SelenideElement notification = $(".Toastify__toast-body");

    //region Get elements methods
    private SelenideElement getDashboardTabByName(String dashboardName) {
        return dashboardsTabs.$(byText(dashboardName));
    }

    private SelenideElement getExitFullScreenButton() {
        return fullScreenControls.hover().$("button[type=button]");
    }

    private SelenideElement getWidgetByName(String widgetName) {
        return widgetsList.find(text(widgetName));
    }

    private SelenideElement getWidgetActionsButton(String widgetName) {
        return getWidgetByName(widgetName).$(".Widget__header").$(".Button");
    }
    //endregion

    //region Alerts steps
    @Step("Close notification")
    public DashboardsPage closeNotification() {
        notification.click();
        //addWidgetButton.hover();
        return this;
    }

    @Step("Confirm alert")
    public DashboardsPage confirmAlert() {
        Selenide.confirm();
        return this;
    }

    @Step("Dismiss alert")
    public DashboardsPage dismissAlert() {
        Selenide.dismiss();
        return this;
    }
    //endregion

    //region Dashboards page header steps
    @Step("Open dashboard page")
    public DashboardsPage openDashboardPage(String dashboardUrl) {
        open(dashboardUrl);
        return this;
    }

    @Step("'New dashboard' button click")
    public DashboardsPage clickNewDashboardButton() {
        newDashboardButton.click();
        return this;
    }

    @Step("Select dashboard '{dashboardName}'")
    public DashboardsPage selectDashboard(String dashboardName) {
        getDashboardTabByName(dashboardName).click();
        return this;
    }

    @Step("Select dashboard action '{actionItem}'")
    public DashboardsPage selectDashboardAction(DashboardActionItem actionItem) {
        dashboardActionsButton.click();
        sleep(1000);
        menuItems.find(text(actionItem.getDisplayedName())).click();
        return this;
    }

    @Step("Check that dashboard '{dashboardName}' exist")
    public DashboardsPage checkThatDashboardExist(String dashboardName) {
        getDashboardTabByName(dashboardName).shouldBe(visible);
        return this;
    }

    @Step("Check that dashboard '{dashboardName}' not exist")
    public DashboardsPage checkThatDashboardNotExist(String dashboardName) {
        getDashboardTabByName(dashboardName).shouldNotBe(visible);
        return this;
    }

    @Step("Exit full-screen mode")
    public DashboardsPage exitFullScreenMode() {
        getExitFullScreenButton().click();
        return this;
    }

    @Step("Check that full screen mode is on")
    public DashboardsPage checkFullScreenModeIsOn() {
        getExitFullScreenButton().should(visible);
        return this;
    }

    @Step("Check that full screen mode is off")
    public DashboardsPage checkFullScreenModeIsOff() {
        fullScreenControls.shouldNot(exist);
        return this;
    }
    //endregion

    //region Default dashboard widgets steps
    @Step("Check that all widgets on overview tab are displayed")
    public DashboardsPage checkThatWidgetsDisplayed() {
        getWidgetByName("Test cases").should(visible);
        getWidgetByName("Automation").should(visible);
        getWidgetByName("Launches").should(visible);
        getWidgetByName("Automation trend").should(visible);
        getWidgetByName("Mute trend").should(visible);
        return this;
    }
    //endregion

    //region Custom dashboard widgets steps
    @Step("'Add widget' button click")
    public DashboardsPage addWidgetButtonClick() {
        addWidgetButton.click();
        return this;
    }

    @Step("Select widget action '{actionItem}'")
    public DashboardsPage selectWidgetAction(String widgetName, WidgetActionItem actionItem) {
        getWidgetActionsButton(widgetName).click();
        menuItems.find(text(actionItem.getDisplayedName())).click();
        return this;
    }

    @Step("Check that widget '{widgetName}' exist")
    public DashboardsPage checkThatWidgetExist(String widgetName) {
        getWidgetByName(widgetName).should(visible);
        return this;
    }

    @Step("Check that widget '{widgetName}' not exist")
    public DashboardsPage checkThatWidgetNotExist(String widgetName) {
        getWidgetByName(widgetName).shouldNot(visible);
        return this;
    }

    @Step("Check that widget have 'Markdown Articles' content")
    public DashboardsPage checkWidgetHaveMarkdownArticles(String widgetName) {
        getWidgetByName(widgetName).$(".MarkdownArticle").shouldHave(text(widgetName));
        return this;
    }

    @Step("Check that widget have 'Trend chart graph' content")
    public DashboardsPage checkWidgetHaveTrendChartGraph(String widgetName) {
        getWidgetByName(widgetName).$(".TrendChart__graph").should(visible);
        return this;
    }

    @Step("Check that widget have 'Pie chart graph' content")
    public DashboardsPage checkWidgetHavePieChartGraph(String widgetName) {
        getWidgetByName(widgetName).$(".PieChart__graph").should(visible);
        return this;
    }

    @Step("Check that widget have 'Tree view chart graph' content")
    public DashboardsPage checkWidgetHaveTreeViewChartGraph(String widgetName) {
        getWidgetByName(widgetName).$(".TreeViewChart__graph").should(visible);
        return this;
    }

    @Step("Check that widget have 'Test case row list' content")
    public DashboardsPage checkWidgetHaveTestCaseRowList(String widgetName) {
        getWidgetByName(widgetName).$(".list").should(visible);
        return this;
    }

    @Step("Check that widget have 'Launch row list' content")
    public DashboardsPage checkWidgetHaveLaunchRowList(String widgetName) {
        getWidgetByName(widgetName).$(".list").should(visible);
        return this;
    }
    //endregion
}
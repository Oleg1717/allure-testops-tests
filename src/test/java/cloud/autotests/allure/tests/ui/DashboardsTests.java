package cloud.autotests.allure.tests.ui;

import cloud.autotests.allure.api.steps.DashboardsApi;
import cloud.autotests.allure.config.ConfigHelper;
import cloud.autotests.allure.ui.pages.components.forms.DashboardEditForm;
import cloud.autotests.allure.ui.pages.components.forms.DashboardWidgetEditForm;
import cloud.autotests.allure.ui.pages.components.forms.DeleteForm;
import cloud.autotests.allure.ui.data.FormErrorMessages;
import cloud.autotests.allure.ui.data.TestUrl;
import cloud.autotests.allure.ui.data.dashboards.DashboardActionItem;
import cloud.autotests.allure.ui.data.dashboards.FormGroupByItem;
import cloud.autotests.allure.ui.data.dashboards.FormLaunchAnalyticMetricItem;
import cloud.autotests.allure.ui.data.dashboards.FormTopTestCasesMetricItem;
import cloud.autotests.allure.ui.data.dashboards.FormTreeItem;
import cloud.autotests.allure.ui.data.dashboards.FormTypeItem;
import cloud.autotests.allure.ui.data.dashboards.WidgetActionItem;
import cloud.autotests.allure.ui.helpers.WithLogin;
import cloud.autotests.allure.ui.helpers.allure.annotations.Layer;
import cloud.autotests.allure.ui.pages.DashboardsPage;
import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Lead;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static io.qameta.allure.Allure.parameter;

@Owner("OlegV")
@Layer("ui")
@Severity(SeverityLevel.BLOCKER)
@Epic("Regression")
@Feature("Dashboards")
@Tags({@Tag("dashboards"), @Tag("microservice")})
//@Tag("dashboards")
public class DashboardsTests extends TestBase {

    DashboardsPage dashboardsPage = new DashboardsPage();
    DashboardEditForm dashboardEditForm = new DashboardEditForm();
    DashboardWidgetEditForm dashboardWidgetEditForm = new DashboardWidgetEditForm();
    DeleteForm deleteForm = new DeleteForm();

    static String dashboardForTestsUrl;

    @BeforeAll
    static void addDashboardForTests() {
        dashboardForTestsUrl = TestUrl.DASHBOARDS + DashboardsApi.addDashboard("ForWidgetsTests");
    }

    @AfterAll
    static void deleteDashboardsAfterTests() {
        DashboardsApi.deleteAllDashboards(ConfigHelper.APP_CONFIG.projectId());
    }

    //region Add new dashboards tests
    @WithLogin
    @Test
    @AllureId("5562")
    @Tag("positive_only")
    @Tag("positive_negative")
    @Story("Add new dashboards")
    @DisplayName("Add new dashboard")
    void addDashboard() {
        String dashboardName = "NewDashboard";
        dashboardsPage.openDashboardPage(TestUrl.DASHBOARDS)
                .clickNewDashboardButton();
        dashboardEditForm.setNameInput(dashboardName)
                .clickSubmitButton();
        dashboardsPage.checkThatDashboardExist(dashboardName);
    }

    @WithLogin
    @Test
    @Tag("positive_negative")
    @Tag("positive_only")
    @AllureId("5572")
    @Story("Add new dashboards")
    @DisplayName("Add dashboard with blank name")
    void addDashboardWithBlankName() {
        String dashboardName = "";
        dashboardsPage.openDashboardPage(TestUrl.DASHBOARDS)
                .clickNewDashboardButton();
        dashboardEditForm.setNameInput(dashboardName)
                .clickSubmitButton()
                .checkThatNameErrorMessageIs(FormErrorMessages.NAME_IS_REQUIRED.error());
    }

    @WithLogin
    @Test
    @AllureId("5576")
    @Story("Add new dashboards")
    @DisplayName("Add dashboard with only space characters in name")
    void addDashboardWithSpacesName() {
        String dashboardName = "   ";
        dashboardsPage.openDashboardPage(TestUrl.DASHBOARDS)
                .clickNewDashboardButton();
        dashboardEditForm.setNameInput(dashboardName)
                .clickSubmitButton()
                .checkThatNameErrorMessageIs(FormErrorMessages.NAME_SHOULD_NOT_BE_BLANK.error());
    }

    @WithLogin
    @Test
    @Tag("positive_negative")
    @AllureId("5569")
    @Story("Add new dashboards")
    @DisplayName("Cancel adding new dashboard using cancel button")
    void cancelAddingDashboardByCancelButton() {
        String dashboardName = "CancelButton";
        dashboardsPage.openDashboardPage(TestUrl.DASHBOARDS)
                .clickNewDashboardButton();
        dashboardEditForm.setNameInput(dashboardName)
                .clickCancelButton();
        dashboardsPage.confirmAlert()
                .checkThatDashboardNotExist(dashboardName);
    }

    @WithLogin
    @Test
    @Tag("positive_only")
    @Tag("positive_negative")
    @AllureId("5561")
    @Story("Add new dashboards")
    @DisplayName("Cancel adding new dashboard using close button")
    void cancelAddingDashboardByCloseButton() {
        String dashboardName = "CloseButton";
        dashboardsPage.openDashboardPage(TestUrl.DASHBOARDS)
                .clickNewDashboardButton();
        dashboardEditForm.setNameInput(dashboardName)
                .clickCloseButton();
        dashboardsPage.confirmAlert()
                .checkThatDashboardNotExist(dashboardName);
    }

    @WithLogin
    @Test
    @AllureId("5574")
    @Story("Add new dashboards")
    @DisplayName("Cancel adding new dashboard with blank name using cancel button")
    void cancelAddingDashboardWithBlankNameByCancelButton() {
        String dashboardName = "";
        dashboardsPage.openDashboardPage(TestUrl.DASHBOARDS)
                .clickNewDashboardButton();
        dashboardEditForm.setNameInput(dashboardName)
                .clickCancelButton()
                .checkThatFormIsClosed();
    }

    @WithLogin
    @Test
    @AllureId("5563")
    @Story("Add new dashboards")
    @DisplayName("Cancel adding new dashboard with blank name using close button")
    void cancelAddingDashboardWithBlankNameByCloseButton() {
        String dashboardName = "";
        dashboardsPage.openDashboardPage(TestUrl.DASHBOARDS)
                .clickNewDashboardButton();
        dashboardEditForm.setNameInput(dashboardName)
                .clickCloseButton()
                .checkThatFormIsClosed();
    }
    //endregion

    //region Various dashboard tests
    @WithLogin
    @Test
    @AllureId("5566")
    @Story("Change dashboards")
    @DisplayName("Switch dashboard full screen mode on/off")
    void dashboardFullScreenMode() {
        String dashboardName = "FullScreen";
        String dashboardUrl = TestUrl.DASHBOARDS + DashboardsApi.addDashboard(dashboardName);
        dashboardsPage.openDashboardPage(dashboardUrl)
                .selectDashboardAction(DashboardActionItem.FULLSCREEN_MODE)
                .checkFullScreenModeIsOn()
                .exitFullScreenMode()
                .checkFullScreenModeIsOff();
    }

    @WithLogin
    @Test
    @AllureId("5570")
    @Story("Change dashboards")
    @DisplayName("Edit dashboard name")
    void editDashboardName() {
        String dashboardName = "Edit";
        String newDashboardName = "EditNew";
        String dashboardUrl = TestUrl.DASHBOARDS + DashboardsApi.addDashboard(dashboardName);
        dashboardsPage.openDashboardPage(dashboardUrl)
                .selectDashboardAction(DashboardActionItem.EDIT_DASHBOARD);
        dashboardEditForm.setNameInput(newDashboardName)
                .clickSubmitButton();
        dashboardsPage.checkThatDashboardExist(newDashboardName);
    }

    @WithLogin
    @Test
    @AllureId("5565")
    @Story("Change dashboards")
    @DisplayName("Delete dashboard")
    void deleteDashboard() {
        String dashboardName = "Delete";
        String dashboardUrl = TestUrl.DASHBOARDS + DashboardsApi.addDashboard(dashboardName);
        dashboardsPage.openDashboardPage(dashboardUrl)
                .selectDashboardAction(DashboardActionItem.DELETE_DASHBOARD);
        deleteForm.clickDeleteButton();
        dashboardsPage.checkThatDashboardNotExist(dashboardName);
    }
    //endregion

    //region Standard (Overview) dashboard widgets tests
    @WithLogin
    @Test
    @AllureId("5555")
    @Story("Check Standard (Overview) dashboard widgets")
    @DisplayName("Standard dashboard should contains 5 widgets")
    void defaultDashboardShouldContains5Widgets() {
        dashboardsPage.openDashboardPage(TestUrl.DASHBOARDS)
                .checkThatWidgetsDisplayed();
    }
    //endregion

    //region Custom dashboard widgets tests
    @WithLogin
    @Test
    @AllureId("5571")
    @Story("Change widgets")
    @DisplayName("Add widget using 'Add widget' button")
    void addWidgetByAddWidgetButton() {
        String dashboardName = "ByAddWidgetButton";
        String dashboardUrl = TestUrl.DASHBOARDS + DashboardsApi.addDashboard(dashboardName);
        dashboardsPage.openDashboardPage(dashboardUrl)
                .addWidgetButtonClick();
        dashboardWidgetEditForm.setNameInput(dashboardName)
                .selectTypeItem(FormTypeItem.LAUNCHES)
                .clickSubmitButton();
        dashboardsPage.checkThatWidgetExist(dashboardName);
    }

    @WithLogin
    @Test
    @AllureId("5577")
    @Severity(SeverityLevel.MINOR)
    @Description("Check that widget name and data can be changed")
    @Lead("s_vasenkov")
    @Story("Change widgets")
    @DisplayName("Edit widget")
    void editWidget() {
        String widgetName = "EditWidget";
        String newName = widgetName + "New";
        dashboardsPage.openDashboardPage(dashboardForTestsUrl)
                .selectDashboardAction(DashboardActionItem.ADD_WIDGET);
        dashboardWidgetEditForm.addWidgetWithType(widgetName, FormTypeItem.LAUNCHES);
        dashboardsPage.closeNotification()
                .selectWidgetAction(widgetName, WidgetActionItem.EDIT);
        dashboardWidgetEditForm.addWidgetWithType(newName, FormTypeItem.LAUNCH_STATISTIC_TREND);
        dashboardsPage.checkThatWidgetExist(newName)
                .checkWidgetHaveTrendChartGraph(newName);
    }

/*
    @WithLogin
    @Test
    @AllureId("5577")
    @Story("Change widgets")
    @DisplayName("Edit widget")
    void editWidget() {
        String widgetName = "EditWidget";
        String newName = widgetName + "New";
        dashboardsPage.openDashboardPage(dashboardForTestsUrl)
                .selectDashboardAction(DashboardActionItem.ADD_WIDGET);
        dashboardWidgetEditForm.setNameInput(widgetName)
                .selectTypeItem(FormTypeItem.LAUNCHES)
                .clickSubmitButton();
        dashboardsPage.closeNotification()
                .selectWidgetAction(widgetName, WidgetActionItem.EDIT);
        dashboardWidgetEditForm.setNameInput(newName)
                .selectTypeItem(FormTypeItem.LAUNCH_STATISTIC_TREND)
                .clickSubmitButton();
        dashboardsPage.checkThatWidgetExist(newName)
                .checkWidgetHaveTrendChartGraph(newName);
    }
*/

    @WithLogin
    @Test
    @Tag("positive_negative")
    @AllureId("5568")
    @Story("Change widgets")
    @DisplayName("Clone widget")
    void cloneWidget() {
        String widgetName = "CloneWidget";
        String newName = widgetName + "New";
        dashboardsPage.openDashboardPage(dashboardForTestsUrl)
                .selectDashboardAction(DashboardActionItem.ADD_WIDGET);
        dashboardWidgetEditForm.setNameInput(widgetName)
                .selectTypeItem(FormTypeItem.LAUNCHES)
                .clickSubmitButton();
        dashboardsPage.closeNotification()
                .selectWidgetAction(widgetName, WidgetActionItem.CLONE);
        dashboardWidgetEditForm.setNameInput(newName)
                .selectTypeItem(FormTypeItem.LAUNCHES)
                .clickSubmitButton();
        dashboardsPage.checkThatWidgetExist(newName);
    }

    @WithLogin
    @Test
    @AllureId("5564")
    @Story("Change widgets")
    @DisplayName("Delete widget")
    void deleteWidget() {
        String widgetName = "DeleteWidget";
        dashboardsPage.openDashboardPage(dashboardForTestsUrl)
                .selectDashboardAction(DashboardActionItem.ADD_WIDGET);
        dashboardWidgetEditForm.setNameInput(widgetName)
                .selectTypeItem(FormTypeItem.LAUNCHES)
                .clickSubmitButton();
        dashboardsPage.closeNotification()
                .selectWidgetAction(widgetName, WidgetActionItem.DELETE)
                .checkThatWidgetNotExist(widgetName);
    }
    //endregion

    //region Add some types of widgets
    @WithLogin
    @Test
    @AllureId("5558")
    @Story("Add some types of widgets")
    @DisplayName("Add widget with type 'Markdown'")
    void addMarkdownTypeWidget() {
        String widgetName = FormTypeItem.MARKDOWN.toString();
        dashboardsPage.openDashboardPage(dashboardForTestsUrl)
                .selectDashboardAction(DashboardActionItem.ADD_WIDGET);
        dashboardWidgetEditForm.setNameInput(widgetName)
                .selectTypeItem(FormTypeItem.MARKDOWN)
                .fillContentWriteTextArea(widgetName)
                .clickSubmitButton();
        dashboardsPage.checkWidgetHaveMarkdownArticles(widgetName);
    }

    @WithLogin
    @Test
    @AllureId("5557")
    @Story("Add some types of widgets")
    @DisplayName("Add widget with type 'Launch statistic Trend'")
    void addLaunchStatisticTrendTypeWidget() {
        String widgetName = FormTypeItem.LAUNCH_STATISTIC_TREND.toString();
        dashboardsPage.openDashboardPage(dashboardForTestsUrl)
                .selectDashboardAction(DashboardActionItem.ADD_WIDGET);
        dashboardWidgetEditForm.setNameInput(widgetName)
                .selectTypeItem(FormTypeItem.LAUNCH_STATISTIC_TREND)
                .clickSubmitButton();
        dashboardsPage.checkWidgetHaveTrendChartGraph(widgetName);
    }

    @WithLogin
    @Test
    @AllureId("5560")
    @Story("Add some types of widgets")
    @DisplayName("Add widget with type 'Automation Trend'")
    void addAutomationTrendTypeWidget() {
        String widgetName = FormTypeItem.AUTOMATION_TREND.toString();
        dashboardsPage.openDashboardPage(dashboardForTestsUrl)
                .selectDashboardAction(DashboardActionItem.ADD_WIDGET);
        dashboardWidgetEditForm.setNameInput(widgetName)
                .selectTypeItem(FormTypeItem.AUTOMATION_TREND)
                .clickSubmitButton();
        dashboardsPage.checkWidgetHaveTrendChartGraph(widgetName);
    }

    @WithLogin
    @Test
    @AllureId("5556")
    @Story("Add some types of widgets")
    @DisplayName("Add widget with type 'Launches'")
    void addLaunchesTypeWidget() {
        String widgetName = FormTypeItem.LAUNCHES.toString();
        dashboardsPage.openDashboardPage(dashboardForTestsUrl)
                .selectDashboardAction(DashboardActionItem.ADD_WIDGET);
        dashboardWidgetEditForm.setNameInput(widgetName)
                .selectTypeItem(FormTypeItem.LAUNCHES)
                .clickSubmitButton();
        dashboardsPage.checkWidgetHaveLaunchRowList(widgetName);
    }

    @WithLogin
    @Story("Add widgets with type 'Launch Analytics'")
    @ParameterizedTest(name = "Add widget with: 'Metric' item = {0}")
    @AllureId("5573")
    @EnumSource(value = FormLaunchAnalyticMetricItem.class)
    void addLaunchAnalyticsWidgets(FormLaunchAnalyticMetricItem item) {
        parameter("'Metric' item", item.toString());
        String widgetName = item.toString();
        dashboardsPage.openDashboardPage(dashboardForTestsUrl)
                .selectDashboardAction(DashboardActionItem.ADD_WIDGET);
        dashboardWidgetEditForm.setNameInput(widgetName)
                .selectTypeItem(FormTypeItem.LAUNCH_ANALYTICS)
                .selectLaunchAnalyticMetricItem(item)
                .clickSubmitButton();
        dashboardsPage.checkWidgetHaveTrendChartGraph(widgetName);
    }

    @WithLogin
    @Story("Add widgets with type 'Test Case Pie Chart'")
    @ParameterizedTest(name = "Add widget with: 'Group by' item = {0}")
    @AllureId("5567")
    @EnumSource(value = FormGroupByItem.class)
    void addTestCasePieChartWidgets(FormGroupByItem item) {
        parameter("'Group by' item", item.toString());
        String widgetName = item.toString();
        dashboardsPage.openDashboardPage(dashboardForTestsUrl)
                .selectDashboardAction(DashboardActionItem.ADD_WIDGET);
        dashboardWidgetEditForm.setNameInput(widgetName)
                .selectTypeItem(FormTypeItem.TEST_CASE_PIE_CHART)
                .selectGroupByItem(item)
                .clickSubmitButton();
        dashboardsPage.checkWidgetHavePieChartGraph(widgetName);
    }

    @WithLogin
    @Story("Add widgets with type 'Top Test Cases'")
    @ParameterizedTest(name = "Add widget with: 'Metric' item = {0}")
    @AllureId("5575")
    @EnumSource(value = FormTopTestCasesMetricItem.class)
    void addTopTestCasesWidgets(FormTopTestCasesMetricItem item) {
        parameter("'Metric' item", item.toString());
        String widgetName = item.toString();
        dashboardsPage.openDashboardPage(dashboardForTestsUrl)
                .selectDashboardAction(DashboardActionItem.ADD_WIDGET);
        dashboardWidgetEditForm.setNameInput(widgetName)
                .selectTypeItem(FormTypeItem.TOP_TEST_CASES)
                .selectTopTestCasesMetricItem(item)
                .clickSubmitButton();
        dashboardsPage.checkWidgetHaveTestCaseRowList(widgetName);
    }

    @WithLogin
    @Story("Add widgets with type 'Test Case Tree Map Chart'")
    @ParameterizedTest(name = "Add widget with: 'Tree' item = {0}")
    @AllureId("5559")
    @EnumSource(value = FormTreeItem.class)
    void addTestCaseTreeMapChartWidgets(FormTreeItem item) {
        parameter("'Tree' item", item.toString());
        String widgetName = item.toString();
        dashboardsPage.openDashboardPage(dashboardForTestsUrl)
                .selectDashboardAction(DashboardActionItem.ADD_WIDGET);
        dashboardWidgetEditForm.setNameInput(widgetName)
                .selectTypeItem(FormTypeItem.TEST_CASE_TREE_MAP_CHART)
                .selectTreeItem(item)
                .clickSubmitButton();
        dashboardsPage.checkWidgetHaveTreeViewChartGraph(widgetName);
    }
    //endregion
}

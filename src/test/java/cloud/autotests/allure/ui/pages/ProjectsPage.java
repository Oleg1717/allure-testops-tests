package cloud.autotests.allure.ui.pages;

import cloud.autotests.allure.ui.data.ProjectsPaginationItem;
import cloud.autotests.allure.ui.components.Sidebar;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;

public class ProjectsPage {

    private Sidebar sidebar = new Sidebar();

    private SelenideElement header = $(".HomeLayout__filter");
    private ElementsCollection headerButtons = header.$$("button[type=button]");
    private SelenideElement myProjectsButton = headerButtons.find(text("My projects"));
    private SelenideElement favoritesButton = headerButtons.find(text("Favorites"));
    private SelenideElement allProjectsButton = headerButtons.find(text("All projects"));
    private SelenideElement findProjectInput = header.$("input[type=search]");
    private SelenideElement newProjectButton = headerButtons.find(text("New project"));

    private ElementsCollection projectsList = $(".ColumnHeaders__content").$$(".list-row");

    private SelenideElement paginationSelect = $(".Pagination__select");
    private SelenideElement paginationIndexes = $(".Pagination__indexes");

    private ElementsCollection paginationSelectItems = $(".css-11unzgr").$$("div");
    private SelenideElement notification = $(".Toastify__toast-body");

    private SelenideElement getProjectRowNameLink(String projectName) {
        return $(byLinkText(projectName));
    }

    public Sidebar getSidebar() {
        return sidebar;
    }

    //region Alerts steps
    @Step("Close notification")
    public ProjectsPage closeNotification() {
        notification.click();
        return this;
    }

    @Step("Confirm alert")
    public ProjectsPage confirmAlert() {
        Selenide.confirm();
        return this;
    }

    @Step("Dismiss alert")
    public ProjectsPage dismissAlert() {
        Selenide.dismiss();
        return this;
    }
    //endregion

    @Step("Open allure projects page")
    public ProjectsPage openProjectsPage(String projectsUrl) {
        open(projectsUrl);
        return this;
    }

    @Step("'All projects' button click")
    public ProjectsPage allProjectsButtonClick() {
        allProjectsButton.click();
        return this;
    }

    @Step("'New project' button click")
    public ProjectsPage newProjectButtonClick() {
        newProjectButton.click();
        return this;
    }

    @Step("Navigate to project `{projectName}`")
    public ProjectsPage navigateTo(String projectName) {
        getProjectRowNameLink(projectName).click();
        return this;
    }

    @Step("Check that project '{projectName}' exist")
    public ProjectsPage checkThatProjectExist(String projectName) {
        findProjectInput.val(projectName);
        getProjectRowNameLink(projectName).shouldBe(visible);
        return this;
    }

    @Step("Select pagination item '{paginationItem}'")
    public ProjectsPage selectPaginationElement(ProjectsPaginationItem paginationItem) {
        paginationSelect.click();
        paginationSelectItems.find(text(paginationItem.toString())).click();
        return this;
    }

    @Step("Check that displayed projects count = {paginationItem}")
    public ProjectsPage checkDisplayedProjectsCount(ProjectsPaginationItem paginationItem) {
        projectsList.shouldHave(size(paginationItem.getItemsPerPage()));
        return this;
    }

    @Step("Check that all projects count = ")
    public ProjectsPage checkPaginationItemsCount(int allProjectsCount) {
        paginationIndexes.shouldHave(text(format("of %d items",allProjectsCount)));
        return this;
    }
}

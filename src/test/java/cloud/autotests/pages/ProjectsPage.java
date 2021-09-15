package cloud.autotests.pages;

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

public class ProjectsPage {

    private SelenideElement header = $(".BasicLayout__header");
    private SelenideElement headerName = header.$(".BreadCrumbs");
    private SelenideElement findProjectInput = header.$("input[type=search]");
    private SelenideElement newProjectButton = header.$("button[type=button]");

    private SelenideElement body = $(".BasicLayout__body");
    private ElementsCollection projectsList = body.$$(".ProjectCard");
    private SelenideElement paginationSelect = body.$(".css-19attta-control");
    private ElementsCollection paginationSelectItems = $(".css-11unzgr").$$("div");
    private SelenideElement toastifyCloseButton = $(".Toastify__close-button");

    //region Get elements methods
    private SelenideElement getProjectRowNameLink(String projectName) {
        return $(byLinkText(projectName));
    }
    //region

    //region Alerts steps
    @Step("Close toastify")
    public ProjectsPage closeToastify() {
        toastifyCloseButton.click();
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
    public ProjectsPage selectPaginationElement(String paginationItem) {
        paginationSelect.click();
        paginationSelectItems.find(text(paginationItem)).click();
        return this;
    }

    @Step("Check that projects list count = {paginationCount}")
    public ProjectsPage checkProjectsListCount(int paginationCount) {
        projectsList.shouldHave(size(paginationCount));
        return this;
    }
}

package cloud.autotests.allure.tests.ui;

import cloud.autotests.allure.api.models.projects.Project;
import cloud.autotests.allure.api.steps.ProjectsApi;
import cloud.autotests.allure.ui.components.Sidebar;
import cloud.autotests.allure.ui.components.forms.ProjectEditForm;
import cloud.autotests.allure.ui.data.FormErrorMessages;
import cloud.autotests.allure.ui.data.TestUrl;
import cloud.autotests.allure.ui.data.projects.ProjectsPaginationItem;
import cloud.autotests.allure.ui.data.sidebar.SideMenuNavItem;
import cloud.autotests.allure.ui.helpers.WithLogin;
import cloud.autotests.allure.ui.helpers.allure.AutoMember;
import cloud.autotests.allure.ui.helpers.allure.Layer;
import cloud.autotests.allure.ui.helpers.allure.ManualMember;
import cloud.autotests.allure.ui.pages.DashboardsPage;
import cloud.autotests.allure.ui.pages.ProjectsPage;
import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Lead;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static io.qameta.allure.Allure.parameter;

@Owner("OlegV")
@Layer("ui")
@Feature("Projects")
@Tags({@Tag("projects"), @Tag("microservice")})
public class ProjectsTests extends TestBase {

    ProjectsPage projectsPage = new ProjectsPage();
    ProjectEditForm projectEditForm = new ProjectEditForm();
    ProjectsApi projectsApi = new ProjectsApi();
    Sidebar sidebar = new Sidebar();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @AllureId("6607")
    @Lead("s_vasenkov")
    @AutoMember("Auto Member")
    @ManualMember("Manual Member")
    @WithLogin
    @Story("Projects page")
    @DisplayName("Check project page")
    void checkProjectPage() {
        projectsPage.openProjectsPage(TestUrl.MAIN_SCREEN)
                .checkProjectPageElements();
    }

    @Test
    @WithLogin
    @Severity(SeverityLevel.MINOR)
    @AllureId("5547")
    @Lead("s_vasenkov")
    @Story("Add new projects")
    @Description("Check that that new project may be added")
    @DisplayName("Add project")
    void addNewProject() {
        String projectName = "ov-new-project";
        projectsPage.openProjectsPage(TestUrl.MAIN_SCREEN)
                .newProjectButtonClick();
        projectEditForm.addProjectWithName(projectName);
        sidebar.navigateTo(SideMenuNavItem.TO_THE_MAIN_SCREEN);
        projectsPage.checkThatProjectExist(projectName);
        projectsApi.deleteProjectByName(projectName);
    }

    @Test
    @AllureId("5548")
    @WithLogin
    @Story("Add new projects")
    @DisplayName("Add project with existing name")
    void addNewProjectWithExistingName() {
        String projectName = "C07-Oleg1717-new-project-exist-name";
        Project project = projectsApi.addProject(projectName, true);
        projectsPage.openProjectsPage(TestUrl.MAIN_SCREEN)
                .newProjectButtonClick();
        projectEditForm.setNameInput(projectName)
                .clickSubmitButton()
                .checkThatFormAlertIs(FormErrorMessages.PROJECT_EXIST.error());
        projectsApi.deleteProject(project.getId());
    }

    @Test
    @AllureId("5544")
    @WithLogin
    @Story("Check projects page pagination")
    @DisplayName("Check pagination items count")
    void checkPaginationItemsCount() {
        int projectsCount = projectsApi.getProjectsCount();
        projectsPage.openProjectsPage(TestUrl.MAIN_SCREEN)
                .allProjectsButtonClick()
                .checkPaginationItemsCount(projectsCount);
    }

    @WithLogin
    @Story("Check projects page pagination")
    @ParameterizedTest(name = "Check pagination with item = {0}")
    @AllureId("5546")
    @EnumSource(value = ProjectsPaginationItem.class)
    void paginationTest(ProjectsPaginationItem item) {
        parameter("Items per page", item.getItemsPerPage());
        projectsPage.openProjectsPage(TestUrl.MAIN_SCREEN)
                .allProjectsButtonClick()
                .selectPaginationElement(item)
                .checkDisplayedProjectsCount(item);
    }
}

package cloud.autotests.allure.tests.ui;

import cloud.autotests.allure.api.models.projects.Project;
import cloud.autotests.allure.api.steps.ProjectsApi;
import cloud.autotests.allure.ui.components.forms.ProjectEditForm;
import cloud.autotests.allure.ui.data.FormErrorMessages;
import cloud.autotests.allure.ui.data.TestUrls;
import cloud.autotests.allure.ui.data.projects.ProjectsPaginationItem;
import cloud.autotests.allure.ui.helpers.WithLogin;
import cloud.autotests.allure.ui.helpers.allure.Layer;
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
//@Tag("projects")
public class ProjectsTests extends TestBase {

    ProjectsPage projectsPage = new ProjectsPage();
    ProjectEditForm projectEditForm = new ProjectEditForm();
    ProjectsApi projectsApi = new ProjectsApi();

    @Severity(SeverityLevel.MINOR)
    @Description("Check that that new project may be added")
    @Lead("s_vasenkov")
    @Test
    @AllureId("5548")
    @WithLogin
    @Story("Add new projects")
    @DisplayName("Add project")
    void addNewProject() {
        String projectName = "ov-new-project";
        projectsPage.openProjectsPage(TestUrls.PROJECTS.url())
                .newProjectButtonClick();
        projectEditForm.addProjectWithName(projectName);
        projectsPage.openProjectsPage(TestUrls.PROJECTS.url())
                .checkThatProjectExist(projectName);
        projectsApi.deleteProjectByName(projectName);
    }

/*
    @Test
    @AllureId("5548")
    @WithLogin
    @Story("Add new projects")
    @DisplayName("Add project")
    void addNewProject() {
        String projectName = "C07-Oleg1717-new-project";
        projectsPage.openProjectsPage(TestUrls.PROJECTS.url())
                .newProjectButtonClick();
        projectEditForm.setNameInput(projectName)
                .fillContentWriteTextArea(projectName)
                .clickPublicCheckbox()
                .clickSubmitButton();
        projectsPage.openProjectsPage(TestUrls.PROJECTS.url())
                .checkThatProjectExist(projectName);
        projectsApi.deleteProjectByName(projectName);
    }
*/

    @Test
    @AllureId("5548")
    @WithLogin
    @Story("Add new projects")
    @DisplayName("Add project with existing name")
    void addNewProjectWithExistingName() {
        String projectName = "C07-Oleg1717-new-project-exist-name";
        Project project = projectsApi.addProject(projectName, true);
        projectsPage.openProjectsPage(TestUrls.PROJECTS.url())
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
        projectsPage.openProjectsPage(TestUrls.PROJECTS.url())
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
        projectsPage.openProjectsPage(TestUrls.PROJECTS.url())
                .allProjectsButtonClick()
                .selectPaginationElement(item)
                .checkDisplayedProjectsCount(item);
    }
}

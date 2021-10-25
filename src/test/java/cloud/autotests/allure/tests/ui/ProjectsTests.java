package cloud.autotests.allure.tests.ui;

import cloud.autotests.allure.api.models.projects.Project;
import cloud.autotests.allure.api.steps.ProjectsApi;
import cloud.autotests.allure.ui.components.forms.ProjectEditForm;
import cloud.autotests.allure.ui.data.ErrorMessages;
import cloud.autotests.allure.ui.data.ProjectsPaginationItem;
import cloud.autotests.allure.ui.data.TestData;
import cloud.autotests.allure.ui.helpers.WithLogin;
import cloud.autotests.allure.ui.helpers.allure.Layer;
import cloud.autotests.allure.ui.pages.ProjectsPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static io.qameta.allure.Allure.parameter;

@Owner("OlegV")
@Layer("ui")
@Feature("Projects")
public class ProjectsTests extends TestBase {

    ProjectsPage projectsPage = new ProjectsPage();
    ProjectEditForm projectEditForm = new ProjectEditForm();
    ProjectsApi projectsApi = new ProjectsApi();

    @Test
    @WithLogin
    @Story("Add new projects")
    @DisplayName("Add project")
    void addNewProject() {
        String projectName = "C07-Oleg1717-new-project";
        projectsPage.openProjectsPage(TestData.PROJECTS_URL)
                .newProjectButtonClick();
        projectEditForm.setNameInput(projectName)
                .fillContentWriteTextArea(projectName)
                .clickPublicCheckbox()
                .clickSubmitButton();
        projectsPage.openProjectsPage(TestData.PROJECTS_URL)
                .checkThatProjectExist(projectName);
        projectsApi.deleteProjectByName(projectName);
    }

    @Test
    @WithLogin
    @Story("Add new projects")
    @DisplayName("Add project with existing name")
    void addNewProjectWithExistingName() {
        String projectName = "C07-Oleg1717-new-project-exist-name";
        Project project = projectsApi.addProject(projectName, true);
        projectsPage.openProjectsPage(TestData.PROJECTS_URL)
                .newProjectButtonClick();
        projectEditForm.setNameInput(projectName)
                .clickSubmitButton()
                .checkThatFormAlertIs(ErrorMessages.PROJECT_EXIST);
        projectsApi.deleteProject(project.getId());
    }

    @Test
    @WithLogin
    @Story("Check projects page pagination")
    @DisplayName("Check pagination items count")
    void checkPaginationItemsCount() {
        int projectsCount = projectsApi.getProjectsCount();
        projectsPage.openProjectsPage(TestData.PROJECTS_URL)
                .allProjectsButtonClick()
                .checkPaginationItemsCount(projectsCount);
    }

    @WithLogin
    @Story("Check projects page pagination")
    @ParameterizedTest(name = "Check pagination with item = {0}")
    @EnumSource(value = ProjectsPaginationItem.class)
    void paginationTest(ProjectsPaginationItem item) {
        parameter("Items per page", item.getItemsPerPage());
        projectsPage.openProjectsPage(TestData.PROJECTS_URL)
                .allProjectsButtonClick()
                .selectPaginationElement(item)
                .checkDisplayedProjectsCount(item);
    }
}

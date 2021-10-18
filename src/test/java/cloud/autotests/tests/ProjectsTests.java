package cloud.autotests.tests;

import cloud.autotests.api.ProjectsApi;
import cloud.autotests.config.ConfigHelper;
import cloud.autotests.data.ProjectsPaginationItem;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.pages.ProjectsPage;
import cloud.autotests.pages.components.forms.ProjectEditForm;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static io.qameta.allure.Allure.parameter;

@Owner("Oleg1717")
@Feature("Projects page tests")
public class ProjectsTests extends TestBase {

    ProjectsPage projectsPage = new ProjectsPage();
    ProjectEditForm projectEditForm = new ProjectEditForm();
    ProjectsApi projectsApi = new ProjectsApi();

    static final String PROJECTS_URL = ConfigHelper.getWebUrl();
    static final String PROJECT_EXIST_ALERT = "This name is taken";

    @Test
    @WithLogin
    @Story("Add new project tests")
    @DisplayName("Add new project")
    void addNewProject() {
        String projectName = "C07-Oleg1717-new-project";
        projectsPage.openProjectsPage(PROJECTS_URL)
                .newProjectButtonClick();
        projectEditForm.setNameInput(projectName)
                .fillContentWriteTextArea(projectName)
                .clickPublicCheckbox()
                .clickSubmitButton();
        projectsPage.openProjectsPage(PROJECTS_URL)
                .checkThatProjectExist(projectName);
        projectsApi.deleteProjectByName(projectName);
    }

    @Test
    @WithLogin
    @Story("Add new project tests")
    @DisplayName("Add new project with existing name")
    void addNewProjectWithExistingName() {
        String projectName = "C07-Oleg1717-new-project-exist-name";
        int projectId = projectsApi.addProject(projectName, true);
        projectsPage.openProjectsPage(PROJECTS_URL)
                .newProjectButtonClick();
        projectEditForm.setNameInput(projectName)
                .clickSubmitButton()
                .checkThatFormAlertIs(PROJECT_EXIST_ALERT);
        projectsApi.deleteProjectById(projectId);
    }

    @WithLogin
    @Story("Projects page pagination tests")
    @ParameterizedTest(name = "Check pagination with item = {0}")
    @EnumSource(value = ProjectsPaginationItem.class)
    void paginationTest(ProjectsPaginationItem item) {
        parameter("Items per page", item.getItemsPerPage());
        projectsPage.openProjectsPage(PROJECTS_URL)
                .allProjectsButtonClick()
                .selectPaginationElement(item)
                .checkProjectsListCount(item);
    }
}

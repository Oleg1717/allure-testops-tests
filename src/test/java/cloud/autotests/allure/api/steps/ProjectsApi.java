package cloud.autotests.allure.api.steps;

import cloud.autotests.allure.api.models.projects.Project;
import cloud.autotests.allure.api.models.projects.Projects;
import cloud.autotests.allure.api.requests.ProjectsRequests;
import io.qameta.allure.Step;

import java.util.HashMap;
import java.util.Map;

public class ProjectsApi {

    @Step("Delete project with id = {projectId} using API")
    public void deleteProject(int projectId) {
        ProjectsRequests.getDeleteProjectResponse(projectId);
    }

    @Step("Add project '{projectName}' using API")
    public Project addProject(String projectName, boolean isPublic) {
        Project newProject = Project.builder()
                .name(projectName)
                .isPublic(isPublic)
                .build();
        return ProjectsRequests
                .getNewProjectResponse(newProject)
                .as(Project.class);
    }

    @Step("Delete project '{projectName}' using API")
    public void deleteProjectByName(String projectName) {
        Project project = getProjectByName(projectName);
        deleteProject(project.getId());
    }

    @Step("Get data of project '{projectName}' using API")
    public Project getProjectByName(String projectName) {
        Map<String, String> requestParams = new HashMap<String, String>() {{
            put("v2", "true");
            put("query", projectName);
            put("sort", "id,desc");
            put("size", "500");
        }};
        return ProjectsRequests
                .getProjectsDataResponse(requestParams)
                .as(Projects.class)
                .getProjectsList()
                .stream()
                .filter(project -> project.getName().equals(projectName))
                .findAny()
                .orElse(null);
    }

    @Step("Get projects count by API")
    public int getProjectsCount() {
        Map<String, String> requestParams = new HashMap<String, String>() {{
            put("v2", "true");
            put("query", "");
            put("sort", "id,desc");
            put("size", "1000");
        }};
        return ProjectsRequests
                .getProjectsDataResponse(requestParams)
                .as(Projects.class)
                .getProjectsCount();
    }
}

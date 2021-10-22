package cloud.autotests.api.steps;

import cloud.autotests.api.models.projects.Project;
import cloud.autotests.api.requests.ProjectsRequests;
import io.qameta.allure.Step;

import java.util.HashMap;
import java.util.Map;

public class ProjectsApi {

    @Step("Delete project with id = {projectId} using API")
    public void deleteProject(int projectId) {
        ProjectsRequests.deleteProjectRequest(projectId);
    }

    @Step("Add project '{projectName}' using API")
    public Project addProject(String projectName, boolean isPublic) {
        Project newProject = Project.builder()
                .name(projectName)
                .isPublic(isPublic)
                .build();
        return ProjectsRequests.addProjectRequest(newProject);
    }

    @Step("Delete project '{projectName}' using API")
    public void deleteProjectByName(String projectName) {
        Project project = getProjectDataByName(projectName);
        deleteProject(project.getId());
    }

    @Step("Get data of project '{projectName}' using API")
    public Project getProjectDataByName(String projectName) {
        Map<String, String> requestParams = new HashMap<String, String>() {{
            put("v2", "true");
            put("query", projectName);
            put("sort", "id%2Cdesc");
            put("size", "500");
        }};
        return ProjectsRequests.getProjectsDataRequest(requestParams)
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
            put("sort", "id%2Cdesc");
            put("size", "5000");
        }};
        return ProjectsRequests.getProjectsDataRequest(requestParams).getProjectsCount();
    }
}

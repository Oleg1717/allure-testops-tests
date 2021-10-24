package cloud.autotests.allure.api.steps;

import cloud.autotests.allure.api.models.projects.Project;
import cloud.autotests.allure.api.requests.ProjectsRequests;
import io.qameta.allure.Step;

import java.util.HashMap;
import java.util.Map;

import static cloud.autotests.allure.api.requests.ProjectsRequests.getProjectsData;

public class ProjectsApi {

    @Step("Delete project with id = {projectId} using API")
    public void deleteProject(int projectId) {
        ProjectsRequests.deleteProject(projectId);
    }

    @Step("Add project '{projectName}' using API")
    public Project addProject(String projectName, boolean isPublic) {
        Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setIsPublic(isPublic);
        return ProjectsRequests.addProject(newProject);
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
        return getProjectsData(requestParams)
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
        return ProjectsRequests.getProjectsData(requestParams).getTotalElements();
    }
}

package cloud.autotests.api.steps;

import cloud.autotests.api.models.projects.Project;
import cloud.autotests.api.models.projects.Projects;
import cloud.autotests.api.helpers.EndPoints;
import cloud.autotests.api.requests.ProjectsRequests;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cloud.autotests.api.helpers.RestAssuredSpec.spec;
import static io.restassured.RestAssured.given;

public class ProjectsApi {

    ProjectsRequests requests = new ProjectsRequests();

    @Step("Delete project with id = {projectId}")
    public void deleteProject(int projectId) {
        requests.deleteProjectRequest(projectId);
    }

    @Step("Add project '{projectName}' using API")
    public Project addProject(String projectName, boolean isPublic) {
        Project newProject = Project.builder()
                .name(projectName)
                .isPublic(isPublic)
                .build();
        return requests.addProjectRequest(newProject);
    }

    @Step("Delete project '{projectName}' using API")
    public void deleteProjectByName(String projectName) {
        Project project = getProjectByName(projectName);
        deleteProject(project.getId());
    }

    @Step("Get id of project '{projectName}' using API")
    public Project getProjectByName(String projectName) {
        Map<String, String> requestParams = new HashMap<String, String>() {{
            put("v2", "true");
            put("query", projectName);
            put("sort", "id%2Cdesc");
            put("size", "500");
        }};

        List<Project> projects = requests.getProjectsDataRequest(requestParams).getProjectsList();
        return projects.stream()
                .filter(prj -> projectName.equals(prj.getName()))
                .findAny()
                .orElse(null);
    }
}

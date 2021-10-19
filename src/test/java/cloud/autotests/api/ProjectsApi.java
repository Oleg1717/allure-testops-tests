package cloud.autotests.api;

import cloud.autotests.api.model.Project;
import cloud.autotests.api.model.Projects;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

import static cloud.autotests.api.spec.RestAssuredSpec.spec;
import static io.restassured.RestAssured.given;

public class ProjectsApi {

    public Projects getProjectsListResponse(Map<String, String> requestParams) {
        return given()
                .spec(spec().request())
                .params(requestParams)
                .when()
                .get("/project")
                .then()
                .statusCode(200)
                .extract().as(Projects.class);
    }

    public Project getProjectDataResponse(int projectId) {
        return given()
                .spec(spec().request())
                .when()
                .get("/project/{projectId}", projectId)
                .then()
                .statusCode(200)
                .extract().as(Project.class);
    }

    public Project getNewProjectResponse(Project projectData) {
        return given()
                .spec(spec().request())
                .contentType(ContentType.JSON)
                .body(projectData)
                .when()
                .post("/project")
                .then()
                .statusCode(200)
                .extract().as(Project.class);
    }

    @Step("Delete project with id = {projectId}")
    public void deleteProjectById(int projectId) {
        given()
                .spec(spec().request())
                .when()
                .delete("/project/" + projectId)
                .then()
                .statusCode(204);
    }

    @Step("Add project '{projectName}' using API")
    public int addProject(String projectName, boolean isPublic) {
        Project project = new Project(projectName, isPublic);
        return getNewProjectResponse(project).getId();
    }


    @Step("Delete project '{projectName}' using API")
    public void deleteProjectByName(String projectName) {
        int projectId = getProjectIdByName(projectName);
        deleteProjectById(projectId);
    }

    @Step("Get id of project '{projectName}'")
    public int getProjectIdByName(String projectName) {
        Map<String, String> requestParams = new HashMap<String, String>() {{
            put("v2", "true");
            put("query", projectName);
            put("sort", "id%2Cdesc");
            put("size", "500");
        }};

        Project[] projects = getProjectsListResponse(requestParams).getContent();
        return projects[0].getId();
    }
}

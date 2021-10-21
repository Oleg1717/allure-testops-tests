package cloud.autotests.api.requests;

import cloud.autotests.api.models.projects.Project;
import cloud.autotests.api.models.projects.Projects;
import io.restassured.http.ContentType;

import java.util.Map;

import static cloud.autotests.api.helpers.RestAssuredSpec.spec;
import static io.restassured.RestAssured.given;

public class ProjectsRequests {

    public Projects getProjectsDataRequest(Map<String, String> requestParams) {
        return given()
                .spec(spec().request())
                .params(requestParams)
                .when()
                .get("/project")
                .then()
                .statusCode(200)
                .extract().as(Projects.class);
    }

    public Project getProjectDataRequest(int projectId) {
        return given()
                .spec(spec().request())
                .when()
                .get("/project/{projectId}", projectId)
                .then()
                .statusCode(200)
                .extract().as(Project.class);
    }

    public Project getProjectStatsRequest(int projectId) {
        return given()
                .spec(spec().request())
                .when()
                .get("/project/{projectId}/stats", projectId)
                .then()
                .statusCode(200)
                .extract().as(Project.class);
    }

    public Project addProjectRequest(Project projectData) {
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

    public Project editProjectRequest(Project projectData) {
        return given()
                .spec(spec().request())
                .contentType(ContentType.JSON)
                .body(projectData)
                .when()
                .patch("/project")
                .then()
                .statusCode(200)
                .extract().as(Project.class);
    }

    public void deleteProjectRequest(int projectId) {
        given()
                .spec(spec().request())
                .when()
                .delete("/project/{projectId}", projectId)
                .then()
                .statusCode(204);
    }
}

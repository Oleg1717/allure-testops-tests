package cloud.autotests.allure.api.requests;

import cloud.autotests.allure.api.helpers.EndPoints;
import cloud.autotests.allure.api.models.projects.Project;
import cloud.autotests.allure.api.models.projects.Projects;
import io.restassured.http.ContentType;

import java.util.Map;

import static cloud.autotests.allure.api.helpers.RestAssuredSpec.spec;
import static io.restassured.RestAssured.given;

public class ProjectsRequests {

    public static Projects getProjectsDataRequest(Map<String, String> requestParams) {
        return given()
                .spec(spec().request())
                .params(requestParams)
                .when()
                .get(EndPoints.PROJECT)
                .then()
                .statusCode(200)
                .extract().as(Projects.class);
    }

    public static Project getProjectDataRequest(int projectId) {
        return given()
                .spec(spec().request())
                .when()
                .get(EndPoints.PROJECT_ID, projectId)
                .then()
                .statusCode(200)
                .extract().as(Project.class);
    }

    public static Project getProjectStatsRequest(int projectId) {
        return given()
                .spec(spec().request())
                .when()
                .get(EndPoints.PROJECT_STATS, projectId)
                .then()
                .statusCode(200)
                .extract().as(Project.class);
    }

    public static Project addProjectRequest(Project projectData) {
        return given()
                .spec(spec().request())
                .contentType(ContentType.JSON)
                .body(projectData)
                .when()
                .post(EndPoints.PROJECT)
                .then()
                .statusCode(200)
                .extract().as(Project.class);
    }

    public static Project editProjectRequest(Project projectData) {
        return given()
                .spec(spec().request())
                .contentType(ContentType.JSON)
                .body(projectData)
                .when()
                .patch(EndPoints.PROJECT)
                .then()
                .statusCode(200)
                .extract().as(Project.class);
    }

    public static void deleteProjectRequest(int projectId) {
        given()
                .spec(spec().request())
                .when()
                .delete(EndPoints.PROJECT_ID, projectId)
                .then()
                .statusCode(204);
    }
}

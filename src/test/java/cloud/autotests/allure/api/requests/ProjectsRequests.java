package cloud.autotests.allure.api.requests;

import cloud.autotests.allure.api.data.EndPoints;
import cloud.autotests.allure.api.models.projects.Project;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static cloud.autotests.allure.api.helpers.RestAssuredSpec.spec;
import static io.restassured.RestAssured.given;

public class ProjectsRequests {

    public static Response getProjectsDataResponse(Map<String, String> requestParams) {
        return given()
                .spec(spec().rsRequest())
                .params(requestParams)
                .when()
                .get(EndPoints.PROJECT)
                .then()
                .extract().response();
    }

    public static Response getProjectDataResponse(int projectId) {
        return given()
                .spec(spec().rsRequest())
                .when()
                .get(EndPoints.PROJECT_ID, projectId)
                .then()
                .extract().response();
    }

    public static Response getProjectStatsResponse(int projectId) {
        return given()
                .spec(spec().rsRequest())
                .when()
                .get(EndPoints.PROJECT_STATS, projectId)
                .then()
                .extract().response();
    }

    public static Response getNewProjectResponse(Project projectData) {
        return given()
                .spec(spec().rsRequest())
                .contentType(ContentType.JSON)
                .body(projectData)
                .when()
                .post(EndPoints.PROJECT)
                .then()
                .extract().response();
    }

    public static Response getEditProjectResponse(Project projectData) {
        return given()
                .spec(spec().rsRequest())
                .contentType(ContentType.JSON)
                .body(projectData)
                .when()
                .patch(EndPoints.PROJECT)
                .then()
                .extract().response();
    }

    public static Response getDeleteProjectResponse(int projectId) {
        return given()
                .spec(spec().rsRequest())
                .when()
                .delete(EndPoints.PROJECT_ID, projectId)
                .then()
                .extract().response();
    }
}

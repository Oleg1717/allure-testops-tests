package cloud.autotests.allure.api.requests;

import cloud.autotests.allure.api.data.EndPoints;
import cloud.autotests.allure.api.models.projects.Project;
import cloud.autotests.allure.api.models.projects.Projects;
import io.restassured.http.ContentType;

import java.util.Map;

import static cloud.autotests.allure.api.helpers.RestAssuredSpec.spec;
import static io.restassured.RestAssured.given;

public class ProjectsRequests {

    public static Projects getProjectsData(Map<String, String> requestParams) {
        return given()
                .spec(spec().rsRequest())
                .params(requestParams)
                .when()
                .get(EndPoints.PROJECT)
                .then()
                .statusCode(200)
                .extract().as(Projects.class);
    }

    public static Project getProjectData(int projectId) {
        return given()
                .spec(spec().rsRequest())
                .when()
                .get(EndPoints.PROJECT_ID, projectId)
                .then()
                .statusCode(200)
                .extract().as(Project.class);
    }

    public static Project getProjectStats(int projectId) {
        return given()
                .spec(spec().rsRequest())
                .when()
                .get(EndPoints.PROJECT_STATS, projectId)
                .then()
                .statusCode(200)
                .extract().as(Project.class);
    }

    public static Project addProject(Project projectData) {
        return given()
                .spec(spec().rsRequest())
                .contentType(ContentType.JSON)
                .body(projectData)
                .when()
                .post(EndPoints.PROJECT)
                .then()
                .statusCode(200)
                .extract().as(Project.class);
    }

    public static Project editProject(Project projectData) {
        return given()
                .spec(spec().rsRequest())
                .contentType(ContentType.JSON)
                .body(projectData)
                .when()
                .patch(EndPoints.PROJECT)
                .then()
                .statusCode(200)
                .extract().as(Project.class);
    }

    public static void deleteProject(int projectId) {
        given()
                .spec(spec().rsRequest())
                .when()
                .delete(EndPoints.PROJECT_ID, projectId)
                .then()
                .statusCode(204);
    }
}

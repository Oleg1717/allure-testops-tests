package cloud.autotests.api;

import cloud.autotests.api.model.Project;
import cloud.autotests.config.ConfigHelper;
import cloud.autotests.helpers.AllureRestAssuredFilter;
import io.qameta.allure.Step;

import static cloud.autotests.api.AuthorizationData.authorizationData;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class ProjectsApi {

    @Step("Add project '{projectName}' using API")
    public int addProject(String projectName, boolean isPublic) {
        Project project = new Project(projectName, isPublic);
        return given()
                .baseUri(ConfigHelper.getApiBaseUri())
                .basePath(ConfigHelper.getApiBasePath())
                .header("X-XSRF-TOKEN", ConfigHelper.getXsrfToken())
                .cookies(authorizationData().getSessionCookies())
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .log().all()
                .contentType(JSON)
                .body(project)
                .when()
                .post("/project")
                .then()
                .statusCode(200)
                .extract().path("id");
    }

    @Step("Delete project with id = {projectId} using API")
    public void deleteProjectById(int projectId) {
        given()
                .baseUri(ConfigHelper.getApiBaseUri())
                .basePath(ConfigHelper.getApiBasePath())
                .header("X-XSRF-TOKEN", ConfigHelper.getXsrfToken())
                .cookies(authorizationData().getSessionCookies())
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .log().uri()
                .when()
                .delete("/project/" + projectId)
                .then()
                .statusCode(204);
    }

    @Step("Delete project '{projectName}' using API")
    public void deleteProjectByName(String projectName) {
        int projectId = getProjectIdByName(projectName);
        deleteProjectById(projectId);
    }

    public Project[] getProjectsList() {
        return given()
                .baseUri(ConfigHelper.getApiBaseUri())
                .basePath(ConfigHelper.getApiBasePath())
                .cookies(authorizationData().getSessionCookies())
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .log().uri()
                .when()
                .get("/project")
                .then()
                .statusCode(200)
                .extract().as(Project[].class);
    }

    public int getProjectIdByName(String projectName) {
        return given()
                .baseUri(ConfigHelper.getApiBaseUri())
                .basePath(ConfigHelper.getApiBasePath())
                .cookies(authorizationData().getSessionCookies())
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .log().uri()
                .when()
                .param("name", projectName)
                .get("/project")
                .then()
                .statusCode(200)
                .extract().path("[0].id");
    }
}

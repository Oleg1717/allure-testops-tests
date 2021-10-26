package cloud.autotests.allure.api.requests;

import cloud.autotests.allure.api.data.ApiEndpoint;
import cloud.autotests.allure.api.models.jobs.Job;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static cloud.autotests.allure.api.helpers.RestAssuredSpec.spec;
import static io.restassured.RestAssured.given;

public class JobsRequests {

    public static Response getJobsData(Map<String, String> requestParams) {
        return given()
                .spec(spec().rsRequest())
                .params(requestParams)
                .when()
                .get(ApiEndpoint.JOB.path())
                .then()
                .extract().response();
    }

    public static Response addJob(Job jobData) {
        return given()
                .spec(spec().rsRequest())
                .contentType(ContentType.JSON)
                .body(jobData)
                .when()
                .post(ApiEndpoint.JOB.path())
                .then()
                .extract().response();
    }

    public static void deleteJob(int jobId) {
        given()
                .spec(spec().rsRequest())
                .when()
                .delete(ApiEndpoint.JOB_ID.path(), jobId)
                .then()
                .statusCode(204);
    }
}

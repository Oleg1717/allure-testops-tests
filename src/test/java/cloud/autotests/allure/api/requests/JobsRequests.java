package cloud.autotests.allure.api.requests;

import cloud.autotests.allure.api.data.ApiEndpoint;
import cloud.autotests.allure.api.models.jobs.Job;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static cloud.autotests.allure.api.helpers.RestAssuredSpec.spec;
import static io.restassured.RestAssured.given;

public class JobsRequests {

    public static Response getJobsDataResponse(Map<String, String> requestParams) {
        return given()
                .spec(spec().rsRequest())
                .params(requestParams)
                .when()
                .get(ApiEndpoint.JOB)
                .then()
                .extract().response();
    }

    public static Response getAddJobResponse(Job jobData) {
        return given()
                .spec(spec().rsRequest())
                .contentType(ContentType.JSON)
                .body(jobData)
                .when()
                .post(ApiEndpoint.JOB)
                .then()
                .extract().response();
    }

    public static void getDeleteJobResponse(int jobId) {
        given()
                .spec(spec().rsRequest())
                .when()
                .delete(ApiEndpoint.JOB_ID, jobId)
                .then()
                .statusCode(204);
    }
}

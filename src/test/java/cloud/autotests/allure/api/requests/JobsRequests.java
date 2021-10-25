package cloud.autotests.allure.api.requests;

import cloud.autotests.allure.api.data.EndPoints;
import cloud.autotests.allure.api.models.jobs.Job;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static cloud.autotests.allure.api.helpers.RestAssuredSpec.spec;
import static io.restassured.RestAssured.given;

public class JobsRequests {

    public static Response getJobsData(Map<String, String> requestParams) {
        return given()
                .spec(spec().request())
                .params(requestParams)
                .when()
                .get(EndPoints.JOB)
                .then()
                .extract().response();
    }

    public static Response addJob(Job jobData) {
        return given()
                .spec(spec().request())
                .contentType(ContentType.JSON)
                .body(jobData)
                .when()
                .post(EndPoints.JOB)
                .then()
                .extract().response();
    }

    public static void deleteJob(int jobId) {
        given()
                .spec(spec().request())
                .when()
                .delete(EndPoints.JOB_ID, jobId)
                .then()
                .statusCode(204);
    }
}

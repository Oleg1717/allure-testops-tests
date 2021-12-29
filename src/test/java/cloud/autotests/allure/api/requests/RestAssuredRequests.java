package cloud.autotests.allure.api.requests;

import cloud.autotests.allure.api.models.ElementBody;
import cloud.autotests.allure.api.models.ModelsInterface;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static cloud.autotests.allure.api.helpers.RestAssuredSpec.spec;
import static io.restassured.RestAssured.given;

public class RestAssuredRequests <T> {

    public static Response collectionDataResponse(String endpoint, Map<String, String> requestParams) {
        return given()
                .spec(spec().rsRequest())
                .params(requestParams)
                .when()
                .get(endpoint)
                .then()
                .extract().response();
    }

    public static Response elementDataResponse(String endpoint, int elementId) {
        return given()
                .spec(spec().rsRequest())
                .when()
                .get(endpoint, elementId)
                .then()
                .extract().response();
    }

    public static Response newElementResponse(String endpoint, ModelsInterface requestBody) {
        return given()
                .spec(spec().rsRequest())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(endpoint)
                .then()
                .extract().response();
    }

    public static Response editElementResponse(String endpoint, int elementId, ModelsInterface requestBody) {
        return given()
                .spec(spec().rsRequest())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch(endpoint, elementId)
                .then()
                .extract().response();
    }

    public static Response deleteElementResponse(String endpoint, int id) {
        return given()
                .spec(spec().rsRequest())
                .when()
                .delete(endpoint, id)
                .then()
                .extract().response();
    }
}

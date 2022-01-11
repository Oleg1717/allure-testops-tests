package cloud.autotests.allure.api.requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static cloud.autotests.allure.api.helpers.RestAssuredSpec.spec;
import static io.restassured.RestAssured.given;

public class AllureRsRequests {

    public static Response getCollectionData(String endpoint, Map<String, String> requestParams) {
        return given()
                .spec(spec().rsRequest())
                .params(requestParams)
                .when()
                .get(endpoint)
                .then()
                .extract().response();
    }

    public static Response getElementData(String endpoint, int elementId) {
        return given()
                .spec(spec().rsRequest())
                .when()
                .get(endpoint, elementId)
                .then()
                .extract().response();
    }

    public static <T> Response addElement(String endpoint, T requestBody) {
        return given()
                .spec(spec().rsRequest())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(endpoint)
                .then()
                .extract().response();
    }

    public static <T> Response editElement(String endpoint, int elementId, T requestBody) {
        return given()
                .spec(spec().rsRequest())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch(endpoint, elementId)
                .then()
                .extract().response();
    }

    public static Response deleteElement(String endpoint, int id) {
        return given()
                .spec(spec().rsRequest())
                .when()
                .delete(endpoint, id)
                .then()
                .extract().response();
    }
}

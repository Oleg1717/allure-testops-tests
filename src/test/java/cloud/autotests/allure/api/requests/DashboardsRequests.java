package cloud.autotests.allure.api.requests;

import cloud.autotests.allure.api.data.ApiEndpoint;
import cloud.autotests.allure.api.models.dashboards.Dashboard;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static cloud.autotests.allure.api.helpers.RestAssuredSpec.spec;
import static io.restassured.RestAssured.given;

public class DashboardsRequests {

    public static Response getDashboardsDataResponse(Map<String, String> requestParams) {
        return given()
                .spec(spec().rsRequest())
                .params(requestParams)
                .when()
                .get(ApiEndpoint.DASHBOARD.path())
                .then()
                .extract().response();
    }

    public static Response getDashboardDataResponse(int dashboardId) {
        return given()
                .spec(spec().rsRequest())
                .when()
                .get(ApiEndpoint.DASHBOARD_ID.path(), dashboardId)
                .then()
                .extract().response();
    }

    public static Response getNewDashboardResponse(Dashboard dashboardData) {
        return given()
                .spec(spec().rsRequest())
                .contentType(ContentType.JSON)
                .body(dashboardData)
                .when()
                .post(ApiEndpoint.DASHBOARD.path())
                .then()
                .extract().response();
    }

    public static Response getEditDashboardResponse(Dashboard dashboardData) {
        return given()
                .spec(spec().rsRequest())
                .contentType(ContentType.JSON)
                .body(dashboardData)
                .when()
                .patch(ApiEndpoint.DASHBOARD_ID.path(), dashboardData.getId())
                .then()
                .extract().response();
    }

    public static Response getDeleteDashboardResponse(int dashboardId) {
        return given()
                .spec(spec().rsRequest())
                .when()
                .delete(ApiEndpoint.DASHBOARD_ID.path(), dashboardId)
                .then()
                .extract().response();
    }
}

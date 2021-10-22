package cloud.autotests.allure.api.requests;

import cloud.autotests.allure.api.data.EndPoints;
import cloud.autotests.allure.api.models.dashboards.Dashboard;
import cloud.autotests.allure.api.models.dashboards.Dashboards;
import io.restassured.http.ContentType;

import static cloud.autotests.allure.api.helpers.RestAssuredSpec.spec;
import static io.restassured.RestAssured.given;

public class DashboardsRequests {

    public static Dashboards getDashboardsData(int projectId) {
        return given()
                .spec(spec().request())
                .param("projectId", projectId)
                .param("size", "500")
                .when()
                .get(EndPoints.DASHBOARD)
                .then()
                .statusCode(200)
                .extract().as(Dashboards.class);
    }

    public static Dashboard getDashboardData(int dashboardId) {
        return given()
                .spec(spec().request())
                .when()
                .get(EndPoints.DASHBOARD_ID, dashboardId)
                .then()
                .statusCode(200)
                .extract().as(Dashboard.class);
    }

    public static Dashboard addDashboard(Dashboard dashboard) {
        return given()
                .spec(spec().request())
                .contentType(ContentType.JSON)
                .body(dashboard)
                .when()
                .post(EndPoints.DASHBOARD)
                .then()
                .statusCode(200)
                .extract().as(Dashboard.class);
    }

    public static Dashboard editDashboard(Dashboard dashboardData) {
        return given()
                .spec(spec().request())
                .contentType(ContentType.JSON)
                .body(dashboardData)
                .when()
                .patch(EndPoints.DASHBOARD_ID, dashboardData.getId())
                .then()
                .statusCode(200)
                .extract().as(Dashboard.class);
    }

    public static void deleteDashboard(int dashboardId) {
        given()
                .spec(spec().request())
                .when()
                .delete(EndPoints.DASHBOARD_ID, dashboardId)
                .then()
                .statusCode(202);
    }
}

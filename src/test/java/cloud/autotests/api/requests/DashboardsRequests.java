package cloud.autotests.api.requests;

import cloud.autotests.api.helpers.EndPoints;
import cloud.autotests.api.models.dashboards.Dashboard;
import cloud.autotests.api.models.dashboards.Dashboards;
import cloud.autotests.api.models.dashboards.Widget;
import cloud.autotests.api.models.projects.Project;
import io.restassured.http.ContentType;

import java.util.List;

import static cloud.autotests.api.helpers.RestAssuredSpec.spec;
import static io.restassured.RestAssured.given;

public class DashboardsRequests {

    public static Dashboards getDashboardsDataRequest(int projectId) {
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

    public static Dashboard getDashboardDataRequest(int dashboardId) {
        return given()
                .spec(spec().request())
                .when()
                .get(EndPoints.DASHBOARD_ID, dashboardId)
                .then()
                .statusCode(200)
                .extract().as(Dashboard.class);
    }

    public static Dashboard addDashboardRequest(Dashboard dashboard) {
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

    public static Dashboard editDashboardRequest(Dashboard dashboardData) {
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

    public static void deleteDashboardRequest(int dashboardId) {
        given()
                .spec(spec().request())
                .when()
                .delete(EndPoints.DASHBOARD_ID, dashboardId)
                .then()
                .statusCode(202);
    }
}

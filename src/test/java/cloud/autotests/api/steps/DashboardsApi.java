package cloud.autotests.api.steps;

import cloud.autotests.api.helpers.EndPoints;
import cloud.autotests.api.models.dashboards.Dashboard;
import cloud.autotests.api.models.dashboards.Dashboards;
import cloud.autotests.config.ConfigHelper;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import java.util.List;

import static cloud.autotests.api.helpers.RestAssuredSpec.spec;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class DashboardsApi {

    //region Dashboards

    public Dashboards getDashboardsListResponse(int projectId) {
        return given()
                .spec(spec().request())
                .param("projectId", projectId)
                .param("size", 2000)
                .when()
                .get(EndPoints.DASHBOARD)
                .then()
                .statusCode(200)
                .extract().as(Dashboards.class);
    }

    public Dashboard getNewDashboardResponse(Dashboard dashboard) {
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

    @Step("Add dashboard '{dashboardName}' using API")
    public Integer addDashboard(String dashboardName) {
        Dashboard dashboard = Dashboard.builder()
                .projectId(ConfigHelper.getProjectId())
                .name(dashboardName)
                .build();
        return getNewDashboardResponse(dashboard).getId();
    }

    public void deleteDashboard(int dashboardId) {
        given()
                .spec(spec().request())
                .pathParam("id", dashboardId)
                .when()
                .delete(EndPoints.DASHBOARD_ID)
                .then()
                .statusCode(202);
    }

    public void deleteDashboardByName(String dashboardName) {
        int dashboardId = getDashboardIdByName(dashboardName);
        deleteDashboard(dashboardId);
    }

    @Step("Get id of dashboard '{dashboardName}' using API")
    public int getDashboardIdByName(String dashboardName) {
        return given()
                .spec(spec().request())
                .param("projectId", ConfigHelper.getProjectId())
                .param("size", 2000)
                .when()
                .get(EndPoints.DASHBOARD)
                .then()
                .statusCode(200)
                .extract().path(format("content.find { it.name == '%s' }.id", dashboardName));
    }

    @Step("Delete all tests dashboards using API")
    public void deleteAllDashboards(int projectId) {
        List<Dashboard> dashboardsList = getDashboardsListResponse(projectId).getDashboardsList();

        for (Dashboard dashboard : dashboardsList) {
            deleteDashboard(dashboard.getId());
        }
    }
    //endregion

    //region Dashboard widgets
/*    public List<Widget> getWidgetsList(String dashboardId) {
        return given()
                .spec(spec().request())
                .pathParam("id", dashboardId)
                .when()
                .get(EndPoints.DASHBOARD_ID)
                .then()
                .statusCode(200)
                .extract().as(Widgets.class).getWidgetsList();
    }*/

    public void deleteWidget(int widgetId) {
        given()
                .spec(spec().request())
                .pathParam("id", widgetId)
                .when()
                .delete(EndPoints.WIDGET_ID)
                .then()
                .statusCode(202);
    }

/*    public void deleteAllWidgets(String dashboardId) {
        List<Widget> widgetList = getWidgetsList(dashboardId);
        for (Widget widget : widgetList) {
            deleteWidget(widget.getWidgetId());
        }
    }*/
    //endregion
}

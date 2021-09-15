package cloud.autotests.api;

import cloud.autotests.api.model.dashboards.Dashboard;
import cloud.autotests.api.model.dashboards.DashboardsInfo;
import cloud.autotests.api.model.dashboards.Widget;
import cloud.autotests.api.model.dashboards.WidgetsInfo;
import cloud.autotests.config.ConfigHelper;
import io.qameta.allure.Step;

import java.util.List;

import static cloud.autotests.api.spec.RestAssuredSpec.spec;
import static io.restassured.http.ContentType.JSON;
import static java.lang.String.format;

public class DashboardsApi {

    //region Dashboards
    @Step("Add dashboard using API")
    public int addDashboard(String dashboardName) {
        Dashboard dashboard = new Dashboard(ConfigHelper.getProjectId(), dashboardName);

        return spec().request()
                .contentType(JSON)
                .body(dashboard)
                .when()
                .post("dashboard")
                .then()
                .statusCode(200)
                .extract().path("id");
    }

    public void deleteDashboardById(int dashboardId) {
        spec().request()
                .when()
                .delete("dashboard/" + dashboardId)
                .then()
                .statusCode(202);
    }

    public void deleteDashboardByName(String dashboardName) {
        int dashboardId = getDashboardIdByName(dashboardName);
        deleteDashboardById(dashboardId);
    }

    public int getDashboardIdByName(String dashboardName) {
        return spec().request()
                .when()
                .param("projectId", ConfigHelper.getProjectId())
                .param("size", 2000)
                .get("dashboard/")
                .then()
                .statusCode(200)
                .extract().path(format("content.find { it.name == '%s' }.id", dashboardName));
    }

    public List<Dashboard> getDashboardsList(int projectId) {
        return spec().request()
                .when()
                .param("projectId", projectId)
                .param("size", 2000)
                .get("dashboard/")
                .then()
                .statusCode(200)
                .extract().as(DashboardsInfo.class).getDashboardsList();
    }

    @Step("Delete all tests dashboards using API")
    public void deleteAllDashboards(int projectId) {
        List<Dashboard> dashboardsList = getDashboardsList(projectId);

        for (Dashboard dashboard : dashboardsList) {
            deleteDashboardById(dashboard.getId());
        }
    }
    //endregion

    //region Dashboard widgets
    public List<Widget> getWidgetsList(String dashboardId) {
        return spec().request()
                .when()
                .get("dashboard/" + dashboardId)
                .then()
                .statusCode(200)
                .extract().as(WidgetsInfo.class).getWidgetsList();
    }

    public void deleteWidget(int widgetId) {
        spec().request()
                .when()
                .delete("widget/" + widgetId)
                .then()
                .statusCode(202);
    }

    public void deleteAllWidgets(String dashboardId) {
        List<Widget> widgetList = getWidgetsList(dashboardId);
        for (Widget widget : widgetList) {
            deleteWidget(widget.getWidgetId());
        }
    }
    //endregion
}

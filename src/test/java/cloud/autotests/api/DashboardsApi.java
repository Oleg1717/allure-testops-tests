package cloud.autotests.api;

import cloud.autotests.api.model.dashboards.Dashboard;
import cloud.autotests.api.model.dashboards.Dashboards;
import cloud.autotests.api.model.dashboards.Widget;
import cloud.autotests.api.model.dashboards.Widgets;
import cloud.autotests.config.ConfigHelper;
import io.qameta.allure.Step;

import java.util.List;

import static cloud.autotests.api.spec.RestAssuredSpec.spec;
import static io.restassured.http.ContentType.JSON;

public class DashboardsApi {

    //region Dashboards
    @Step("Add dashboard using API")
    public int addDashboard(String dashboardName) {
        Dashboard dashboard = new Dashboard();
        dashboard.setProjectId(ConfigHelper.getProjectId());
        dashboard.setName(dashboardName);

        return spec().request()
                .contentType(JSON)
                .body(dashboard)
                .when()
                .post("dashboard")
                .then()
                .statusCode(200)
                .extract().path("id");
    }

    public void deleteDashboard(int dashboardId) {
        spec().request()
                .when()
                .delete("dashboard/" + dashboardId)
                .then()
                .statusCode(202);
    }

    public List<Dashboard> getDashboardsList(int projectId) {
        return spec().request()
                .when()
                .param("projectId", projectId)
                .param("size", 2000)
                .get("dashboard/")
                .then()
                .statusCode(200)
                .extract().as(Dashboards.class).getDashboardsList();
    }

    @Step("Delete all tests dashboards using API")
    public void deleteAllDashboards(int projectId) {
        List<Dashboard> dashboardsList = getDashboardsList(projectId);

        for (Dashboard dashboard : dashboardsList) {
            deleteDashboard(dashboard.getId());
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
                .extract().as(Widgets.class).getWidgetsList();
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

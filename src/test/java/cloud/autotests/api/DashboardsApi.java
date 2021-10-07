package cloud.autotests.api;

import cloud.autotests.api.model.dashboards.Dashboard;
import cloud.autotests.api.model.dashboards.DashboardsInfo;
import cloud.autotests.api.model.dashboards.Widget;
import cloud.autotests.api.model.dashboards.WidgetsInfo;
import cloud.autotests.config.ConfigHelper;
import cloud.autotests.helpers.AllureRestAssuredFilter;
import io.qameta.allure.Step;

import java.util.List;

import static cloud.autotests.api.AuthorizationData.authorizationData;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class DashboardsApi {

    //region Dashboards
    @Step("Add dashboard '{dashboardName}' using API")
    public int addDashboard(String dashboardName) {
        Dashboard dashboard = new Dashboard(ConfigHelper.getProjectId(), dashboardName);

        return given()
                .baseUri(ConfigHelper.getApiBaseUri())
                .basePath(ConfigHelper.getApiBasePath())
                .header("X-XSRF-TOKEN", ConfigHelper.getXsrfToken())
                .cookies(authorizationData().getSessionCookies())
                .contentType("application/json; charset=UTF-8")
                .body(dashboard)
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .log().uri()
                .when()
                .post("/dashboard")
                .then()
                .statusCode(200)
                .extract().path("id");
    }

    public void deleteDashboardById(int dashboardId) {
        given()
                .baseUri(ConfigHelper.getApiBaseUri())
                .basePath(ConfigHelper.getApiBasePath())
                .header("X-XSRF-TOKEN", ConfigHelper.getXsrfToken())
                .cookies(authorizationData().getSessionCookies())
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .log().uri()
                .when()
                .delete("/dashboard/" + dashboardId)
                .then()
                .statusCode(202);
    }

    public void deleteDashboardByName(String dashboardName) {
        int dashboardId = getDashboardIdByName(dashboardName);
        deleteDashboardById(dashboardId);
    }

    public int getDashboardIdByName(String dashboardName) {
        return given()
                .baseUri(ConfigHelper.getApiBaseUri())
                .basePath(ConfigHelper.getApiBasePath())
                .cookies(authorizationData().getSessionCookies())
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .log().uri()
                .when()
                .param("projectId", ConfigHelper.getProjectId())
                .param("size", 2000)
                .get("/dashboard")
                .then()
                .statusCode(200)
                .extract().path(format("content.find { it.name == '%s' }.id", dashboardName));
    }

    public List<Dashboard> getDashboardsList(int projectId) {
        return given()
                .baseUri(ConfigHelper.getApiBaseUri())
                .basePath(ConfigHelper.getApiBasePath())
                .cookies(authorizationData().getSessionCookies())
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .log().uri()
                .when()
                .param("projectId", projectId)
                .param("size", 2000)
                .get("/dashboard")
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
        return given()
                .baseUri(ConfigHelper.getApiBaseUri())
                .basePath(ConfigHelper.getApiBasePath())
                .cookies(authorizationData().getSessionCookies())
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .log().uri()
                .when()
                .get("/dashboard/" + dashboardId)
                .then()
                .statusCode(200)
                .extract().as(WidgetsInfo.class).getWidgetsList();
    }

    public void deleteWidget(int widgetId) {
        given()
                .baseUri(ConfigHelper.getApiBaseUri())
                .basePath(ConfigHelper.getApiBasePath())
                .header("X-XSRF-TOKEN", ConfigHelper.getXsrfToken())
                .cookies(authorizationData().getSessionCookies())
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .log().uri()
                .when()
                .delete("/widget/" + widgetId)
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

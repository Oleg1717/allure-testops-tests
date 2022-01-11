package cloud.autotests.allure.api.steps;

import cloud.autotests.allure.api.data.ApiEndpoint;
import cloud.autotests.allure.api.models.dashboards.Dashboard;
import cloud.autotests.allure.api.models.dashboards.Dashboards;
import cloud.autotests.allure.api.requests.DashboardsRequests;
import cloud.autotests.allure.api.requests.AllureRsRequests;
import cloud.autotests.allure.config.ConfigHelper;
import io.qameta.allure.Step;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardsApi {

    @Step("Add dashboard '{dashboardName}' using API")
    public Integer addDashboard(String dashboardName) {
        Dashboard body = Dashboard.builder()
                .projectId(ConfigHelper.getProjectId())
                .name(dashboardName)
                .build();
        return AllureRsRequests
                .addElement(ApiEndpoint.DASHBOARD, body)
                .as(Dashboard.class)
                .getId();
    }

    @Step("Delete dashboard with id = {dashboardId} using API")
    public void deleteDashboard(int dashboardId) {

        AllureRsRequests.deleteElement(ApiEndpoint.DASHBOARD_ID, dashboardId);
        //DashboardsRequests.getDeleteDashboardResponse(dashboardId);
    }

    @Step("Delete dashboard '{dashboardName}' using API")
    public void deleteDashboardByName(int projectId, String dashboardName) {
        Dashboard dashboard = getDashboardIdByName(projectId, dashboardName);
        deleteDashboard(dashboard.getId());
    }

    @Step("Get id of dashboard '{dashboardName}' using API")
    public Dashboard getDashboardIdByName(Integer projectId, String dashboardName) {
        Map<String, String> requestParams = new HashMap<String, String>() {{
            put("projectId", projectId.toString());
            put("size", "5000");
        }};
        return DashboardsRequests
                .getDashboardsDataResponse(requestParams)
                .as(Dashboards.class)
                .getDashboardsList()
                .stream()
                .filter(dashboard -> dashboard.getName().equals(dashboardName))
                .findAny()
                .orElse(null);
    }

    @Step("Delete all tests dashboards using API")
    public void deleteAllDashboards(Integer projectId) {
        Map<String, String> requestParams = new HashMap<String, String>() {{
            put("projectId", projectId.toString());
            put("size", "5000");
        }};
        List<Dashboard> dashboardsList = DashboardsRequests
                .getDashboardsDataResponse(requestParams)
                .as(Dashboards.class)
                .getDashboardsList();

        for (Dashboard dashboard : dashboardsList) {
            deleteDashboard(dashboard.getId());
        }
    }
}

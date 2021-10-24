package cloud.autotests.allure.api.steps;

import cloud.autotests.allure.api.models.dashboards.Dashboard;
import cloud.autotests.allure.api.requests.DashboardsRequests;
import cloud.autotests.allure.config.ConfigHelper;
import io.qameta.allure.Step;

import java.util.List;

public class DashboardsApi {

    @Step("Add dashboard '{dashboardName}' using API")
    public Integer addDashboard(String dashboardName) {
        Dashboard dashboard = Dashboard.builder()
                .projectId(ConfigHelper.getProjectId())
                .name(dashboardName)
                .build();
        return DashboardsRequests.addDashboard(dashboard).getId();
    }

    @Step("Delete dashboard with id = {dashboardId} using API")
    public void deleteDashboard(int dashboardId) {
        DashboardsRequests.deleteDashboard(dashboardId);
    }

    @Step("Delete dashboard '{dashboardName}' using API")
    public void deleteDashboardByName(int projectId, String dashboardName) {
        Dashboard dashboard = getDashboardIdByName(projectId, dashboardName);
        deleteDashboard(dashboard.getId());
    }

    @Step("Get id of dashboard '{dashboardName}' using API")
    public Dashboard getDashboardIdByName(int projectId, String dashboardName) {
        return DashboardsRequests.getDashboardsData(projectId)
                .getDashboardsList()
                .stream()
                .filter(dashboard -> dashboard.getName().equals(dashboardName))
                .findAny()
                .orElse(null);
    }

    @Step("Delete all tests dashboards using API")
    public void deleteAllDashboards(int projectId) {
        List<Dashboard> dashboardsList = DashboardsRequests
                .getDashboardsData(projectId).getDashboardsList();
        for (Dashboard dashboard : dashboardsList) {
            deleteDashboard(dashboard.getId());
        }
    }
}

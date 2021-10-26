package cloud.autotests.allure.api.data;

public enum ApiEndpoint {

    USER_LOGIN("/api/login/system"),
    LICENSE("/license"),
    PROJECT("/project"),
    PROJECT_ID("/project/{projectId}"),
    PROJECT_STATS("/project/{projectId}/stats"),
    DASHBOARD("/dashboard"),
    DASHBOARD_ID("/dashboard/{dashboardId}"),
    WIDGET("/widget"),
    WIDGET_ID("/widget/{widgetId}"),
    WIDGET_DATA("/widget/{widgetId}/data"),
    JOB("/job"),
    JOB_ID("/job/{jobId}"),
    JOB_SYNC("/job/{jobId}/sync");

    private final String path;

    ApiEndpoint(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }
}

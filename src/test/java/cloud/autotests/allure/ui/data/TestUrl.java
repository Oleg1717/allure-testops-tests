package cloud.autotests.allure.ui.data;

import cloud.autotests.allure.config.ConfigHelper;

import static java.lang.String.format;

public class TestUrl {

    public static final String MIN_CONTENT = ConfigHelper.APP_CONFIG.baseUrl() + "favicon.ico";
    public static final String USER_PROFILE = ConfigHelper.APP_CONFIG.baseUrl() + "user/" + ConfigHelper.APP_CONFIG.mainUserId();
    public static final String MAIN_SCREEN = ConfigHelper.APP_CONFIG.baseUrl();
    public static final String PROJECT = ConfigHelper.APP_CONFIG.baseUrl() + format("project/%s/", ConfigHelper.APP_CONFIG.projectId());
    public static final String DASHBOARDS = PROJECT + "dashboards/";
    public static final String JOBS = PROJECT + "jobs/";
}

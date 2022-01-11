package cloud.autotests.allure.ui.data;

import cloud.autotests.allure.config.ConfigHelper;

import static java.lang.String.format;

public class TestUrl {

    public static final String MIN_CONTENT = ConfigHelper.getBaseUrl() + "favicon.ico";
    public static final String USER_PROFILE = ConfigHelper.getBaseUrl() + "user/" + ConfigHelper.getMainUserId();
    public static final String MAIN_SCREEN = ConfigHelper.getBaseUrl();
    public static final String PROJECT = ConfigHelper.getBaseUrl() + format("project/%s/", ConfigHelper.getProjectId());
    public static final String DASHBOARDS = PROJECT + "dashboards/";
    public static final String JOBS = PROJECT + "jobs/";
}

package cloud.autotests.allure.ui.data;

import cloud.autotests.allure.config.ConfigHelper;

import static java.lang.String.format;

public class TestData {

    public static final int PROJECT_ID = ConfigHelper.getProjectId();
    public static final String PROJECTS_URL = ConfigHelper.getWebUrl();


    public static final String PROJECT_URL = ConfigHelper.getWebUrl() + format("/project/%s", PROJECT_ID);
    public static final String DASHBOARDS_URL = PROJECT_URL + "/dashboards";
    public static final String JOBS_URL = PROJECT_URL + "/jobs";

}

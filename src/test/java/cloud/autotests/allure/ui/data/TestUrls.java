package cloud.autotests.allure.ui.data;

import cloud.autotests.allure.config.ConfigHelper;

import static java.lang.String.format;

public enum TestUrls {

    MIN_CONTENT(ConfigHelper.getBaseUrl() + "favicon.ico"),
    USER_PROFILE(ConfigHelper.getBaseUrl() + "user/" + ConfigHelper.getMainUserId()),
    PROJECTS(ConfigHelper.getBaseUrl()),
    PROJECT(ConfigHelper.getBaseUrl() + format("project/%s/", ConfigHelper.getProjectId())),
    DASHBOARDS(PROJECT.url() + "dashboards/"),
    JOBS(PROJECT.url() + "jobs/");

    private final String url;

    TestUrls(String url) {
        this.url = url;
    }

    public String url() {
        return url;
    }
}

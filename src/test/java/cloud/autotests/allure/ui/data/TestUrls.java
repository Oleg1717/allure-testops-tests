package cloud.autotests.allure.ui.data;

import cloud.autotests.allure.config.ConfigHelper;

import static java.lang.String.format;

public enum TestUrls {

    PROJECTS(ConfigHelper.getBaseUrl()),
    PROJECT(PROJECTS.url() + format("project/%s/", ConfigHelper.getProjectId())),
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

package cloud.autotests.allure.api.helpers;

import io.qameta.allure.restassured.AllureRestAssured;

public class CustomLogFilter {
    private static final AllureRestAssured FILTER = new AllureRestAssured();

    private CustomLogFilter() {
    }

    public static CustomLogFilter customLogFilter() {
        return InitLogFilter.logFilter;
    }

    public AllureRestAssured withCustomTemplates() {
        FILTER.setRequestTemplate("request.ftl");
        FILTER.setResponseTemplate("response.ftl");
        return FILTER;

    }

    private static class InitLogFilter {
        private static final CustomLogFilter logFilter = new CustomLogFilter();
    }

/*    public static AllureRestAssured withCustomTemplates() {
        FILTER.setRequestTemplate("request.ftl");
        FILTER.setResponseTemplate("response.ftl");
        FILTER.setResponseAttachmentName("Response");
        return FILTER;
    }*/
}

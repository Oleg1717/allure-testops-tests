package cloud.autotests.allure.api.helpers;

import io.qameta.allure.restassured.AllureRestAssured;

public class RestAssuredFilter {
    private static final AllureRestAssured FILTER = new AllureRestAssured();

    public static AllureRestAssured withCustomTemplates() {
        FILTER.setRequestTemplate("request.ftl");
        FILTER.setResponseTemplate("response.ftl");
        FILTER.setResponseAttachmentName("Response");
        return FILTER;
    }
}

package cloud.autotests.allure.api.helpers;

import cloud.autotests.allure.config.ConfigHelper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

import static cloud.autotests.allure.api.helpers.AuthorizationData.getAuthorizationData;

public class RestAssuredSpec {

    private final RequestSpecification request = new RequestSpecBuilder()
            .setBaseUri(ConfigHelper.getApiBaseUri())
            .setBasePath(ConfigHelper.getApiRsPath())
            .addHeader("X-XSRF-TOKEN", ConfigHelper.getXsrfToken())
            .addCookies(getAuthorizationData().getSessionCookies())
            .addFilter(RestAssuredFilter.withCustomTemplates())
            .log(LogDetail.URI)
            .build();

    public static RestAssuredSpec spec() {
        return new RestAssuredSpec();
    }

    public RequestSpecification request() {
        return request;
    }
}

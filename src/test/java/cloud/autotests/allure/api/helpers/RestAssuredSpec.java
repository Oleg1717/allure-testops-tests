package cloud.autotests.allure.api.helpers;

import cloud.autotests.allure.config.ConfigHelper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

public class RestAssuredSpec {

    private final RequestSpecification rsSpec = new RequestSpecBuilder()
            .setBaseUri(ConfigHelper.getBaseUrl())
            .setBasePath(ConfigHelper.getApiRsPath())
            .addHeader("X-XSRF-TOKEN", AuthData.MAIN_USER.xsrfToken())
            .addCookies(AuthData.MAIN_USER.sessionCookies())
            .addFilter(RestAssuredFilter.withCustomTemplates())
            .log(LogDetail.URI)
            .build();

    private final RequestSpecification uaaSpec = new RequestSpecBuilder()
            .setBaseUri(ConfigHelper.getBaseUrl())
            .setBasePath(ConfigHelper.getApiUaaPath())
            .addHeader("X-XSRF-TOKEN", AuthData.MAIN_USER.xsrfToken())
            .addCookies(AuthData.MAIN_USER.sessionCookies())
            .addFilter(RestAssuredFilter.withCustomTemplates())
            .log(LogDetail.URI)
            .build();

    public static RestAssuredSpec spec() {
        return new RestAssuredSpec();
    }

    public RequestSpecification rsRequest() {
        return rsSpec;
    }

    public RequestSpecification uaaRequest() {
        return uaaSpec;
    }
}

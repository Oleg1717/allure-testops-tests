package cloud.autotests.allure.api.helpers;

import cloud.autotests.allure.config.ConfigHelper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

public class RestAssuredSpec {

    private final RequestSpecification rsSpec = getRequestSpec(ConfigHelper.getApiRsPath());

    private final RequestSpecification uaaSpec = getRequestSpec(ConfigHelper.getApiUaaPath());

    private RequestSpecification getRequestSpec(String basePath) {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigHelper.getBaseUrl())
                .setBasePath(basePath)
                .addHeader("X-XSRF-TOKEN", AuthData.MAIN_USER.xsrfToken())
                .addCookies(AuthData.MAIN_USER.sessionCookies())
                .addFilter(RestAssuredFilter.withCustomTemplates())
                .log(LogDetail.URI)
                .build();
    }

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

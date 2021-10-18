package cloud.autotests.api.spec;

import cloud.autotests.config.ConfigHelper;
import cloud.autotests.helpers.AllureRestAssuredFilter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

import static cloud.autotests.api.AuthorizationData.authorizationData;
import static io.restassured.RestAssured.given;

public class RestAssuredSpec {

    private RequestSpecification request = new RequestSpecBuilder()
            .setBaseUri(ConfigHelper.getApiBaseUri())
            .setBasePath(ConfigHelper.getApiBasePath())
            .addHeader("X-XSRF-TOKEN", ConfigHelper.getXsrfToken())
            .addCookies(authorizationData().getSessionCookies())
            .addFilter(AllureRestAssuredFilter.withCustomTemplates())
            .log(LogDetail.ALL)
            .build();

    public static RestAssuredSpec spec() {
        return new RestAssuredSpec();
    }

    public RequestSpecification request() {
        return request;
    }
}

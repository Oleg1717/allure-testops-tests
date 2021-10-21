package cloud.autotests.api.helpers;

import cloud.autotests.config.ConfigHelper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static cloud.autotests.api.helpers.AuthorizationData.authorizationData;

public class RestAssuredSpec {

    private RequestSpecification request = new RequestSpecBuilder()
            .setBaseUri(ConfigHelper.getApiBaseUri())
            .setBasePath(ConfigHelper.getApiRsPath())
            .addHeader("X-XSRF-TOKEN", ConfigHelper.getXsrfToken())
            .addCookies(authorizationData().getSessionCookies())
            .addFilter(RestAssuredFilter.withCustomTemplates())
            .log(LogDetail.ALL)
            .build();

    public static RestAssuredSpec spec() {
        return new RestAssuredSpec();
    }

    public RequestSpecification request() {
        return request;
    }
}

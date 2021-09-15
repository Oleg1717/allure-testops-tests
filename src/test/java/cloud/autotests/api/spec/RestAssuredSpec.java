package cloud.autotests.api.spec;

import cloud.autotests.config.ConfigHelper;
import cloud.autotests.helpers.AllureRestAssuredFilter;
import io.restassured.specification.RequestSpecification;

import static cloud.autotests.api.AuthorizationData.authorizationData;
import static io.restassured.RestAssured.given;

public class RestAssuredSpec {

    private static final RequestSpecification request = given()
            .baseUri(ConfigHelper.getApiBaseUri())
            .basePath(ConfigHelper.getApiBasePath())
            .header("Authorization", authorizationData().getAccessToken())
            .filter(AllureRestAssuredFilter.withCustomTemplates())
            .log().all();

    public static RestAssuredSpec spec() {
        return new RestAssuredSpec();
    }

    public RequestSpecification request() {
        return request;
    }
}

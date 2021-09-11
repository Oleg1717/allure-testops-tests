package cloud.autotests.api;

import cloud.autotests.api.model.Authorization;
import cloud.autotests.config.ConfigHelper;
import cloud.autotests.helpers.AllureRestAssuredFilter;

import static io.restassured.RestAssured.given;

public class UserApi {

    public Authorization getAuthorizationData() {
        return given()
                .baseUri(ConfigHelper.getApiBaseUri())
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .formParam("grant_type", "apitoken")
                .formParam("scope", "openid")
                .formParam("token", ConfigHelper.getUserToken())
                .when()
                .post("/api/uaa/oauth/token")
                .then()
                .statusCode(200)
                .extract().as(Authorization.class);
    }
}

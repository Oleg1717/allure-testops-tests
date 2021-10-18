package cloud.autotests.api;

import cloud.autotests.config.ConfigHelper;
import cloud.autotests.helpers.AllureRestAssuredFilter;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthorizationApi {

    private Response getAuthorizationResponse() {
        return given()
                .baseUri(ConfigHelper.getApiBaseUri())
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .header("X-XSRF-TOKEN", ConfigHelper.getXsrfToken())
                .cookie("XSRF-TOKEN", ConfigHelper.getXsrfToken())
                .formParam("username", ConfigHelper.getUserLogin())
                .formParam("password", ConfigHelper.getUserPassword())
                .when()
                .post("/api/login/system")
                .then()
                .statusCode(200)
                .extract().response();
    }

    public String getSessionToken() {
        return getAuthorizationResponse().getCookie("ALLURE_TESTOPS_SESSION");
    }
}

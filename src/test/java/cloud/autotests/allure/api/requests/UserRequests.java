package cloud.autotests.allure.api.requests;

import cloud.autotests.allure.api.data.ApiEndpoint;
import cloud.autotests.allure.api.helpers.RestAssuredFilter;
import cloud.autotests.allure.config.ConfigHelper;
import io.restassured.response.Response;

import static cloud.autotests.allure.api.helpers.RestAssuredSpec.spec;
import static io.restassured.RestAssured.given;

public class UserRequests {

    public static Response getAuthorizeResponse(String xsrfToken, String username, String password) {
        return given()
                .baseUri(ConfigHelper.getBaseUrl())
                .filter(new RestAssuredFilter().withCustomTemplates())
                .header("X-XSRF-TOKEN", xsrfToken)
                .cookie("XSRF-TOKEN", xsrfToken)
                .formParam("username", username)
                .formParam("password", password)
                .log().all()
                .when()
                .post(ApiEndpoint.USER_LOGIN)
                .then()
                .extract().response();
    }

    public static Response getLicenseDataResponse() {
        return given()
                .spec(spec().uaaRequest())
                .when()
                .get(ApiEndpoint.LICENSE)
                .then()
                .extract().response();
    }
}

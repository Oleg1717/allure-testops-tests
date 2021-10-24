package cloud.autotests.allure.api.requests;

import cloud.autotests.allure.api.data.EndPoints;
import cloud.autotests.allure.api.helpers.RestAssuredFilter;
import cloud.autotests.allure.config.ConfigHelper;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserRequests {

    public Response getAuthorizeResponse(String xsrfToken, String login, String password) {
        return given()
                .baseUri(ConfigHelper.getApiBaseUri())
                .filter(RestAssuredFilter.withCustomTemplates())
                .header("X-XSRF-TOKEN", xsrfToken)
                .cookie("XSRF-TOKEN", xsrfToken)
                .formParam("username", login)
                .formParam("password", password)
                .log().all()
                .when()
                .post(EndPoints.USER_LOGIN)
                .then()
                .extract().response();
    }
}

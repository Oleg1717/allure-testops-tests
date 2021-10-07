package cloud.autotests.api;

import cloud.autotests.config.ConfigHelper;
import cloud.autotests.helpers.AllureRestAssuredFilter;

import static io.restassured.RestAssured.given;

public class UserApi {

    public String getSessionToken() {
        return given()
                .baseUri(ConfigHelper.getApiBaseUri())
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .header("X-XSRF-TOKEN", ConfigHelper.getXsrfToken())
                .cookie("XSRF-TOKEN", ConfigHelper.getXsrfToken())
                .formParam("username", ConfigHelper.getUserLogin())
                .formParam("password", ConfigHelper.getUserPassword())
                .log().uri()
                .when()
                .post("/api/login/system")
                .then()
                .statusCode(200)
                .extract().response().getCookie("ALLURE_TESTOPS_SESSION");
    }
}

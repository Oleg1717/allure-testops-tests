package cloud.autotests.allure.api.steps;

import cloud.autotests.allure.api.requests.UserRequests;

import static io.restassured.RestAssured.given;

public class UserApi {

    public String getSessionToken() {
        return UserRequests.authorize().getCookie("ALLURE_TESTOPS_SESSION");
    }
}

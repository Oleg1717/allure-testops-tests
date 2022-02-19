package cloud.autotests.allure.api.steps;

import cloud.autotests.allure.api.requests.UserRequests;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class UserApi {

    public static String getSessionToken(String xsrfToken, String login, String password) {
        return UserRequests.getAuthorizeResponse(xsrfToken, login, password)
                .getCookie("ALLURE_TESTOPS_SESSION");
    }

    @Step("Login using API")
    public static Response getAuthorizeData(String xsrfToken, String login, String password) {
        return UserRequests.getAuthorizeResponse(xsrfToken, login, password);
    }

    @Step("Get license data using API")
    public static  Response getLicenseData() {
        return UserRequests.getLicenseDataResponse();
    }

    @Step("Check that response error message is '{expectedErrorMessage}'")
    public static void checkThatResponseErrorIs(String actualErrorMessage, String expectedErrorMessage) {
        assertThat(actualErrorMessage)
                .isEqualTo(expectedErrorMessage);
    }

    @Step("Check that authorization is success")
    public static void checkThatAuthorizationIsSuccess(int status) {
        assertThat(status).isEqualTo(200);
    }

}

package cloud.autotests.allure.api.steps;

import cloud.autotests.allure.api.data.ApiErrorMessage;
import cloud.autotests.allure.api.models.Login;
import cloud.autotests.allure.api.requests.UserRequests;
import io.qameta.allure.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class UserApi {

    public String getSessionToken(String xsrfToken, String login, String password) {
        return new UserRequests().getAuthorizeResponse(xsrfToken, login, password)
                .getCookie("ALLURE_TESTOPS_SESSION");
    }

    public Login getAuthorizeData(String xsrfToken, String login, String password) {
        return new UserRequests().getAuthorizeResponse(xsrfToken, login, password).as(Login.class);
    }

    @Step("Check that response error message is '{expectedErrorMessage}'")
    public void checkThatResponseErrorIs(String actualErrorMessage, ApiErrorMessage expectedErrorMessage) {
        assertThat(actualErrorMessage)
                .isEqualTo(expectedErrorMessage.getErrorName());
    }

    @Step("Check that authorization is success")
    public void checkThatAuthorizationIsSuccess(Login login) {
        assertThat(login.getStatus()).isEqualTo(200);
    }

}

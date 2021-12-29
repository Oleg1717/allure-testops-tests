package cloud.autotests.allure.tests.api;

import cloud.autotests.allure.api.data.LoginErrorMessage;
import cloud.autotests.allure.api.helpers.AuthData;
import cloud.autotests.allure.api.models.user.Login;
import cloud.autotests.allure.api.steps.UserApi;
import cloud.autotests.allure.ui.helpers.allure.Layer;
import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Owner("OlegV")
@Feature("Authorization")
@Layer("api")
@Tag("login")
public class AuthorizationTests {

    private String userName = AuthData.SECOND_USER.username();
    private String userPassword = AuthData.SECOND_USER.password();
    private String xsrfToken = AuthData.SECOND_USER.xsrfToken();
    UserApi userApi = new UserApi();

    @Test
    @AllureId("5607")
    @Story("Authorize with valid data")
    @DisplayName("Check authorization with valid user data")
    public void checkAuthorizationWithValidUserData() {
        //given
        //when
        Response response = userApi.getAuthorizeData(xsrfToken, userName, userPassword);
        //then
        userApi.checkThatAuthorizationIsSuccess(response.as(Login.class).getStatus());
    }

    @Test
    @AllureId("5608")
    @Story("Authorize with wrong data")
    @DisplayName("Check authorization with blank login")
    public void checkAuthorizationWithBlankLogin() {
        //given
        String userName = "";
        //when
        Response response = userApi.getAuthorizeData(xsrfToken, userName, userPassword);
        //then
        userApi.checkThatResponseErrorIs(response.as(Login.class).getMessage(),
                LoginErrorMessage.VALIDATION_ERROR.text());
    }

    @Test
    @AllureId("5604")
    @Story("Authorize with wrong data")
    @DisplayName("Check authorization with wrong login")
    public void checkAuthorizationWithWrongLogin() {
        //given
        String userName = "usr";
        //when
        Response response = userApi.getAuthorizeData(xsrfToken, "usr", userPassword);
        //then
        userApi.checkThatResponseErrorIs(response.as(Login.class).getMessage(),
                LoginErrorMessage.BAD_CREDENTIALS.text());
    }

    @Test
    @AllureId("5609")
    @Story("Authorize with wrong data")
    @DisplayName("Check authorization with blank password")
    public void checkAuthorizationWithBlankPassword() {
        //given
        String userPassword = "";
        //when
        Response response = userApi.getAuthorizeData(xsrfToken, userName, userPassword);
        //then
        userApi.checkThatResponseErrorIs(response.as(Login.class).getMessage(),
                LoginErrorMessage.VALIDATION_ERROR.text());
    }

    @Test
    @AllureId("5603")
    @Story("Authorize with wrong XSRF token")
    @DisplayName("Check authorization with blank XSRF token")
    public void checkAuthorizationWithBlankXsrfToken() {
        //given
        String xsrfToken = "";
        //when
        Response response = userApi.getAuthorizeData(xsrfToken, userName, userPassword);
        //then
        userApi.checkThatResponseErrorIs(response.as(Login.class).getMessage(),
                LoginErrorMessage.AN_EXPECTED_CSRF_TOKEN_CANNOT_BE_FOUND.text());
    }

    @Test
    @AllureId("5606")
    @Story("Authorize with wrong XSRF token")
    @DisplayName("Check authorization with space characters in XSRF token")
    public void checkAuthorizationWithSpacesXsrfToken() {
        //given
        String xsrfToken = "   ";
        //when
        Response response = userApi.getAuthorizeData(xsrfToken, userName, userPassword);
        //then
        userApi.checkThatResponseErrorIs(response.as(Login.class).getMessage(),
                LoginErrorMessage.AN_EXPECTED_CSRF_TOKEN_CANNOT_BE_FOUND.text());
    }
}

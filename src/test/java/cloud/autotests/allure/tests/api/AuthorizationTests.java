package cloud.autotests.allure.tests.api;

import cloud.autotests.allure.api.data.ApiErrorMessage;
import cloud.autotests.allure.api.models.Login;
import cloud.autotests.allure.api.steps.UserApi;
import cloud.autotests.allure.config.ConfigHelper;
import cloud.autotests.allure.ui.helpers.allure.Layer;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Owner("OlegV")
@Layer("api")
@Feature("Authorization tests")
public class AuthorizationTests {

    public static final String userLogin = "testuser";
    public static final String userPassword = "testuser12354";
    UserApi userApi = new UserApi();

    @Test
    @Story("Authorization data tests")
    @DisplayName("Check authorization with valid user data")
    public void checkAuthorizationWithValidUserData() {
        String xsrfToken = ConfigHelper.getXsrfToken();
        Login loginData = userApi.getAuthorizeData(xsrfToken, userLogin, userPassword);
        userApi.checkThatAuthorizationIsSuccess(loginData.getStatus());
    }

    @Test
    @Story("Authorization data tests")
    @DisplayName("Check authorization with blank login")
    public void checkAuthorizationWithBlankLogin() {
        String xsrfToken = ConfigHelper.getXsrfToken();
        String userLogin = "";
        Login loginData = userApi.getAuthorizeData(xsrfToken, userLogin, userPassword);
        userApi.checkThatResponseErrorIs(loginData.getMessage(),
                ApiErrorMessage.VALIDATION_ERROR.getErrorName());
    }

    @Test
    @Story("Authorization data tests")
    @DisplayName("Check authorization with wrong login")
    public void checkAuthorizationWithWrongLogin() {
        String xsrfToken = ConfigHelper.getXsrfToken();
        String userLogin = "usr";
        Login loginData = userApi.getAuthorizeData(xsrfToken, userLogin, userPassword);
        userApi.checkThatResponseErrorIs(loginData.getMessage(),
                ApiErrorMessage.BAD_CREDENTIALS.getErrorName());
    }

    @Test
    @Story("Authorization data tests")
    @DisplayName("Check authorization with blank password")
    public void checkAuthorizationWithBlankPassword() {
        String xsrfToken = ConfigHelper.getXsrfToken();
        String userPassword = "";
        Login loginData = userApi.getAuthorizeData(xsrfToken, userLogin, userPassword);
        userApi.checkThatResponseErrorIs(loginData.getMessage(),
                ApiErrorMessage.VALIDATION_ERROR.getErrorName());
    }

    @Test
    @Story("XSRF token tests")
    @DisplayName("Check authorization with blank XSRF token")
    public void checkAuthorizationWithBlankXsrfToken() {
        String xsrfToken = "";
        Login loginData = userApi.getAuthorizeData(xsrfToken, userLogin, userPassword);
        System.out.println(loginData.toString());
        userApi.checkThatResponseErrorIs(loginData.getMessage(),
                ApiErrorMessage.AN_EXPECTED_CSRF_TOKEN_CANNOT_BE_FOUND.getErrorName());
    }

    @Test
    @Story("XSRF token tests")
    @DisplayName("Check authorization with space characters in XSRF token")
    public void checkAuthorizationWithSpacesXsrfToken() {
        String xsrfToken = "   ";
        Login loginData = userApi.getAuthorizeData(xsrfToken, userLogin, userPassword);
        System.out.println(loginData.toString());
        userApi.checkThatResponseErrorIs(loginData.getMessage(),
                ApiErrorMessage.AN_EXPECTED_CSRF_TOKEN_CANNOT_BE_FOUND.getErrorName());
    }
}

package cloud.autotests.allure.api.helpers;

import cloud.autotests.allure.api.steps.UserApi;
import cloud.autotests.allure.config.ConfigHelper;

public class AuthData {

    private final String xsrfToken = String.valueOf((int) (Math.random() * 1000));
    private final String sessionToken;

    private AuthData() {
        sessionToken = UserApi
                .getSessionToken(xsrfToken, ConfigHelper.APP_CONFIG.mainUserLogin(), ConfigHelper.APP_CONFIG.mainUserPassword());
        System.out.println(sessionToken);
    }

    public static AuthData getAuthData() {
        return InitAuthData.AUTH_DATA;
    }

    private static class InitAuthData {
        private static final AuthData AUTH_DATA = new AuthData();
    }

    public String xsrfToken() {
        return xsrfToken;
    }

    public String sessionToken() {
        return sessionToken;
    }
}

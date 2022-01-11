package cloud.autotests.allure.api.helpers;

import cloud.autotests.allure.api.steps.UserApi;
import cloud.autotests.allure.config.ConfigHelper;

import java.util.HashMap;
import java.util.Map;

public class AuthData {

    private final String xsrfToken = String.valueOf((int) (Math.random() * 1000));
    private final String sessionToken;
    private final Map<String, String> sessionCookies = new HashMap<>();

    private AuthData() {
        sessionToken = new UserApi()
                .getSessionToken(xsrfToken, ConfigHelper.getMainUserLogin(), ConfigHelper.getMainUserPassword());
        sessionCookies.put("XSRF-TOKEN", xsrfToken);
        sessionCookies.put("ALLURE_TESTOPS_SESSION", sessionToken);
    }

    public static AuthData getAuthData() {
        return InitAuthData.AUTH_DATA;
    }

    private static class InitAuthData {
        private static final AuthData AUTH_DATA = new AuthData();
    }

    public String xsrfToken() { return xsrfToken; }

    public String sessionToken() {
        return sessionToken;
    }

    public Map<String, String> sessionCookies() {
        return sessionCookies;
    }
}

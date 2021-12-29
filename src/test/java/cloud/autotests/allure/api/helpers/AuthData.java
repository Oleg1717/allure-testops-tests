package cloud.autotests.allure.api.helpers;

import cloud.autotests.allure.api.steps.UserApi;
import cloud.autotests.allure.config.ConfigHelper;

import java.util.HashMap;
import java.util.Map;

public enum AuthData {
    MAIN_USER(ConfigHelper.getMainUserLogin(), ConfigHelper.getMainUserPassword()),
    SECOND_USER(ConfigHelper.getSecondUserLogin(), ConfigHelper.getSecondUserPassword());

    private String username;
    private String password;
    private String xsrfToken;
    private String sessionToken;
    private Map<String, String> sessionCookies = new HashMap<>();

    AuthData(String username, String password) {
        this.username = username;
        this.password = password;
        xsrfToken = String.valueOf((int) (Math.random() * 1000));
        sessionToken = new UserApi().getSessionToken(xsrfToken, username, password);
        sessionCookies.put("XSRF-TOKEN", xsrfToken);
        sessionCookies.put("ALLURE_TESTOPS_SESSION", sessionToken);
    }

    public String username() { return username; }

    public String password() { return password; }

    public String xsrfToken() { return xsrfToken; }

    public String sessionToken() {
        return sessionToken;
    }

    public Map<String, String> sessionCookies() {
        return sessionCookies;
    }
}

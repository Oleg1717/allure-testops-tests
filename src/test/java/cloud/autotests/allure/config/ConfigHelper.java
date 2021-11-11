package cloud.autotests.allure.config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigHelper {

    //region App config
    private static AppConfig getAppConfig() {
        return ConfigFactory.newInstance()
                .create(AppConfig.class, System.getProperties());
    }

    public static String getWebUrl() {
        return getAppConfig().webUrl();
    }

    public static String getApiBaseUri() {
        return getAppConfig().apiBaseUri();
    }

    public static String getApiRsPath() {
        return getAppConfig().apiRsPath();
    }

    public static String getApiUaaPath() {
        return getAppConfig().apiUaaPath();
    }

    public static Integer getProjectId() {
        return getAppConfig().projectId();
    }

    public static String getUserLogin() {
        return getAppConfig().userLogin();
    }

    public static String getUserPassword() {
        return getAppConfig().userPassword();
    }

    public static String getUser2Login() {
        return getAppConfig().user2Login();
    }

    public static String getUser2Password() {
        return getAppConfig().user2Password();
    }

    public static String getUserToken() {
        return getAppConfig().userToken();
    }

    public static String getXsrfToken() {
        return getAppConfig().xsrfToken();
    }
    //endregion

    //region Project config
    private static ProjectConfig getProjectConfig() {
        return ConfigFactory.newInstance()
                .create(ProjectConfig.class, System.getProperties());
    }

    public static String getBrowserName() {
        return getProjectConfig().browserName();
    }

    public static String getBrowserVersion() {
        return getProjectConfig().browserVersion();
    }

    public static String getBrowserSize() {
        return getProjectConfig().browserSize();
    }

    public static String getBrowserMobileView() {
        return getProjectConfig().browserMobileView();
    }

    public static String getRemoteDriverUrl() {
        return getProjectConfig().remoteDriverUrl();
    }

    public static String getVideoStorageUrl() {
        return getProjectConfig().videoStorageUrl();
    }

    public static boolean isWebMobile() {
        return !getProjectConfig().browserMobileView().equals("");
    }

    public static boolean isRemoteWebDriver() {
        return !getProjectConfig().remoteDriverUrl().equals("");
    }

    public static boolean isVideoOn() {
        return !getProjectConfig().videoStorageUrl().equals("");
    }
    //endregion
}

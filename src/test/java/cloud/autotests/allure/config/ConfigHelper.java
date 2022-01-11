package cloud.autotests.allure.config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigHelper {

    //region App config
    private static AppConfig getAppConfig() {
        return ConfigFactory.newInstance().create(AppConfig.class, System.getProperties());
    }

    public static String getBaseUrl() {
        return getAppConfig().baseUrl();
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

    public static String getMainUserLogin() {
        return getAppConfig().mainUserLogin();
    }

    public static String getMainUserPassword() {
        return getAppConfig().mainUserPassword();
    }

    public static String getMainUserEmail() {
        return getAppConfig().mainUserEmail();
    }

    public static String getMainUserFullName() {
        return getAppConfig().mainUserFullname();
    }

    public static String getMainUserId() {
        return getAppConfig().mainUserId();
    }

    public static String getSecondUserLogin() {
        return getAppConfig().secondUserLogin();
    }

    public static String getSecondUserPassword() {
        return getAppConfig().secondUserPassword();
    }
    //endregion

    //region Project config
    private static ProjectConfig getProjectConfig() {
        return ConfigFactory.newInstance().create(ProjectConfig.class, System.getProperties());
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

    public static String getRemoteDriverUrl() {
        return getProjectConfig().remoteDriverUrl();
    }

    public static String getVideoStorageUrl() {
        return getProjectConfig().videoStorageUrl();
    }

    public static boolean isRemoteWebDriver() {
        return !getProjectConfig().remoteDriverUrl().equals("");
    }

    public static boolean isVideoOn() {
        return !getProjectConfig().videoStorageUrl().equals("");
    }
    //endregion
}

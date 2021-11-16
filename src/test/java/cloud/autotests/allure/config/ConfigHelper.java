package cloud.autotests.allure.config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigHelper {

    //region App config
    private static AppConfig getAppConfig() {
        return ConfigFactory.newInstance().create(AppConfig.class, System.getProperties());
    }

    public static String getBaseUrl() { return getAppConfig().baseUrl(); }

    public static String getApiRsPath() {
        return getAppConfig().apiRsPath();
    }

    public static String getApiUaaPath() {
        return getAppConfig().apiUaaPath();
    }

    public static Integer getProjectId() {
        return getAppConfig().projectId();
    }

    public static String getMainUserName() {
        return getAppConfig().mainUserName();
    }

    public static String getMainUserPassword() {
        return getAppConfig().mainUserPassword();
    }

    public static String getSecondUserName() {
        return getAppConfig().secondUserName();
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

    public static String getSelenoidUrl() { return getProjectConfig().selenoidUrl(); }

    public static String getVideoStorageUrl() {
        return getProjectConfig().videoStorageUrl();
    }

    public static boolean isRemoteWebDriver() { return !getProjectConfig().selenoidUrl().equals(""); }

    public static boolean isVideoOn() {
        return !getProjectConfig().videoStorageUrl().equals("");
    }
    //endregion
}

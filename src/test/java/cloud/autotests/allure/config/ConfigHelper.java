package cloud.autotests.allure.config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigHelper {

    public static final AppConfig APP_CONFIG = ConfigFactory.newInstance()
            .create(AppConfig.class, System.getProperties());

    public static final ProjectConfig PROJECT_CONFIG = ConfigFactory.newInstance()
            .create(ProjectConfig.class, System.getProperties());

    public static boolean isRemoteWebDriver() {
        return !PROJECT_CONFIG.remoteDriverUrl().equals("");
    }
}

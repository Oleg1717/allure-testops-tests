package cloud.autotests.allure.config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigHelper {

    public static final AppConfig APP_CONFIG = ConfigFactory.newInstance()
            .create(AppConfig.class, System.getProperties());

    public static final ProjectConfig PROJECT_CONFIG = ConfigFactory.newInstance()
            .create(ProjectConfig.class, System.getProperties());

    public static boolean IS_REMOTE_WEBDRIVER = !PROJECT_CONFIG.remoteDriverUrl().equals("");
}

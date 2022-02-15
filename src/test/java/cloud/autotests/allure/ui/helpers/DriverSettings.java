package cloud.autotests.allure.ui.helpers;

import cloud.autotests.allure.config.ConfigHelper;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static cloud.autotests.allure.config.ConfigHelper.IS_REMOTE_WEBDRIVER;
import static cloud.autotests.allure.config.ConfigHelper.PROJECT_CONFIG;
import static java.lang.String.format;

public class DriverSettings {

    /*
        private static final List<String> BROWSER_OPTIONS_LIST = Arrays
                .asList("--start-fullscreen", "start-maximized", "--headless", "--no-sandbox", "--disable-infobars",
                        "--disable-popup-blocking", "--disable-notifications", "--lang=en-en");
    */
    private static final Logger LOGGER = LoggerFactory.getLogger("WebDriver settings");

    public static void configure() {
        ChromeOptions options = new ChromeOptions();

        Configuration.baseUrl = ConfigHelper.APP_CONFIG.baseUrl();
        Configuration.browser = PROJECT_CONFIG.browserName();
        Configuration.browserVersion = PROJECT_CONFIG.browserVersion();

        LOGGER.info("Configuring");
        LOGGER.info(format("Base URL: %s", Configuration.baseUrl));
        LOGGER.info(format("Browser name: %s", Configuration.browser));
        LOGGER.info(format("Browser version: %s", Configuration.browserVersion));

        if (IS_REMOTE_WEBDRIVER) {
            options.setCapability("enableVNC", true);
            options.setCapability("enableVideo", true);
            Configuration.remote = PROJECT_CONFIG.remoteDriverUrl();
            Configuration.browserSize = PROJECT_CONFIG.browserSize();
            LOGGER.info(format("Browser size: %s", Configuration.browserSize));
            LOGGER.info(format("Remote run: %s", Configuration.remote));
        } else {
            Configuration.browserSize = null;
            options.addArguments("start-maximized");
            LOGGER.info("Browser run in start-maximized mode");
            LOGGER.info("Local run");
        }
        Configuration.browserCapabilities = options;
        LOGGER.info(format("Capabilities: %s", Configuration.browserCapabilities.asMap().toString()));
        LOGGER.info("Configuring done.");
    }
}

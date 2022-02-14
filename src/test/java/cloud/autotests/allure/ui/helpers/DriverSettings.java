package cloud.autotests.allure.ui.helpers;

import cloud.autotests.allure.config.ConfigHelper;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Arrays;
import java.util.List;

public class DriverSettings {

    private static final List<String> browserOptionsList = Arrays.asList("--no-sandbox", "--disable-infobars",
            "--disable-popup-blocking", "--disable-notifications", "--lang=en-en");

    public static void configure() {
        Configuration.baseUrl = ConfigHelper.APP_CONFIG.baseUrl();
        Configuration.browser = ConfigHelper.PROJECT_CONFIG.browserName();
        Configuration.browserVersion = ConfigHelper.PROJECT_CONFIG.browserVersion();

        ChromeOptions options = new ChromeOptions();
        if (ConfigHelper.IS_REMOTE_WEBDRIVER) {
            options.setCapability("enableVNC", true);
            options.setCapability("enableVideo", true);
            Configuration.remote = ConfigHelper.PROJECT_CONFIG.remoteDriverUrl();
            Configuration.browserSize = ConfigHelper.PROJECT_CONFIG.browserSize();
        } else {
            Configuration.browserSize = null;
            options.addArguments("start-maximized");
        }
        Configuration.browserCapabilities = options;
    }
}

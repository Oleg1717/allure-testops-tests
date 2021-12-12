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

    private static List<String> browserOptionsList = Arrays.asList("--no-sandbox", "--disable-infobars",
            "--disable-popup-blocking", "--disable-notifications", "--lang=en-en");

    public static void configure() {
        Configuration.browser = ConfigHelper.getBrowserName();
        Configuration.browserVersion = ConfigHelper.getBrowserVersion();
        Configuration.browserSize = ConfigHelper.getBrowserSize();
        Configuration.baseUrl = ConfigHelper.getBaseUrl();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (ConfigHelper.isRemoteWebDriver()) {
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.remote = ConfigHelper.getRemoteDriverUrl();
        }

        switch (ConfigHelper.getBrowserName()) {
            case "chrome":
                capabilities.setCapability(ChromeOptions.CAPABILITY,
                        new ChromeOptions().addArguments(browserOptionsList));
                break;
            case "firefox":
                capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS,
                        new OperaOptions().addArguments(browserOptionsList));
                break;
            case "opera":
                OperaOptions operaOptions = new OperaOptions()
                        .addArguments(browserOptionsList);
                capabilities.setCapability(OperaOptions.CAPABILITY,
                        new OperaOptions().addArguments(browserOptionsList));
        }

        Configuration.browserCapabilities = capabilities;
    }
}

package cloud.autotests.helpers;

import cloud.autotests.config.ConfigHelper;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static cloud.autotests.api.helpers.AuthorizationData.authorizationData;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        step("Set auth token to browser cookies", () -> {
            step("Open browser with minimum content", () -> open("/favicon.ico"));
            step("Add cookie in browser cookie storage", () -> {
                WebDriverRunner.getWebDriver().manage().addCookie(
                        new Cookie("XSRF-TOKEN", ConfigHelper.getXsrfToken()));
                WebDriverRunner.getWebDriver().manage().addCookie(
                        new Cookie("ALLURE_TESTOPS_SESSION", authorizationData().getSessionToken()));
            });

        });
    }
}

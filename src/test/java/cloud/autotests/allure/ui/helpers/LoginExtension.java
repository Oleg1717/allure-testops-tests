package cloud.autotests.allure.ui.helpers;

import cloud.autotests.allure.api.helpers.AuthorizationData;
import cloud.autotests.allure.config.ConfigHelper;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static cloud.autotests.allure.api.helpers.AuthorizationData.getAuthorizationData;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        String sessionToken = getAuthorizationData().getSessionToken();
        step("Set session token to browser cookies", () -> {
            step("Open browser with minimum content", () -> open("/favicon.ico"));
            step("Add cookie in browser cookie storage", () -> {
                WebDriverRunner.getWebDriver().manage().addCookie(
                        new Cookie("XSRF-TOKEN", ConfigHelper.getXsrfToken()));
                WebDriverRunner.getWebDriver().manage().addCookie(
                        new Cookie("ALLURE_TESTOPS_SESSION", sessionToken));
            });

        });
    }
}
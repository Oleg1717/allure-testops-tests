package cloud.autotests.allure.ui.helpers;

import cloud.autotests.allure.api.helpers.AuthData;
import cloud.autotests.allure.ui.data.TestUrls;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        step("Set session token to browser cookies", () -> {
            step("Open browser with minimum content", () -> open(TestUrls.MIN_CONTENT.url()));
            step("Add cookie in browser cookie storage", () -> {
                WebDriverRunner.getWebDriver().manage().addCookie(
                        new Cookie("XSRF-TOKEN", AuthData.MAIN_USER.xsrfToken()));
                WebDriverRunner.getWebDriver().manage().addCookie(
                        new Cookie("ALLURE_TESTOPS_SESSION", AuthData.MAIN_USER.sessionToken()));
            });

        });
    }
}

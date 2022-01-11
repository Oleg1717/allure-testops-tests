package cloud.autotests.allure.tests.ui;

import cloud.autotests.allure.config.ConfigHelper;
import cloud.autotests.allure.ui.components.Sidebar;
import cloud.autotests.allure.ui.data.sidebar.SideMenuNavItem;
import cloud.autotests.allure.ui.helpers.allure.Layer;
import cloud.autotests.allure.ui.pages.LoginPage;
import cloud.autotests.allure.ui.pages.ProjectsPage;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static cloud.autotests.allure.api.helpers.AuthData.getAuthData;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Owner("OlegV")
@Feature("Authorization")
@Layer("ui")
@Tag("login")
public class AuthorizationTests extends TestBase {

    LoginPage loginPage = new LoginPage();
    Sidebar sidebar = new Sidebar();

    @Test
    @AllureId("5611")
    @Story("Authorize with valid data")
    @DisplayName("Successful login as testuser")
    void loginTest() {
        loginPage.openLoginPage("")
                .setUsernameInput(ConfigHelper.getMainUserLogin())
                .setPasswordInput(ConfigHelper.getMainUserLogin())
                .clickContinueButton();
        sidebar.navigateTo(SideMenuNavItem.USER_MENU)
                .checkUsername(ConfigHelper.getMainUserLogin());
    }

    @Test
    @AllureId("5610")
    @Story("Authorize with valid data")
    @DisplayName("Successful login with localStorage (API + UI)")
    void loginWithCookieTest() {
        step("Set auth tokens to browser cookies", () -> {
            step("Open minimal content, because localstorage can be set when site is opened", () ->
                    open("/favicon.ico"));

            step("Set auths token to to browser cookies", () -> {
                WebDriverRunner.getWebDriver().manage().addCookie(
                        new Cookie("XSRF-TOKEN", getAuthData().xsrfToken()));
                WebDriverRunner.getWebDriver().manage().addCookie(
                        new Cookie("ALLURE_TESTOPS_SESSION", getAuthData().sessionToken()));
            });
        });

        step("Open main page", () ->
                open(""));

        step("Verify successful authorization", () ->
                $("img.Avatar__img").shouldHave(
                        attribute("alt", ConfigHelper.getMainUserLogin())));
    }
}

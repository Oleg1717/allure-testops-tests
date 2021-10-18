package cloud.autotests.tests;

import cloud.autotests.config.ConfigHelper;
import cloud.autotests.data.sidebar.SideMenuNavItem;
import cloud.autotests.helpers.CustomTestWatcher;
import cloud.autotests.pages.LoginPage;
import cloud.autotests.pages.ProjectsPage;
import cloud.autotests.pages.components.Sidebar;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Cookie;

import static cloud.autotests.api.AuthorizationData.authorizationData;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Feature("Login tests")
@ExtendWith(CustomTestWatcher.class)
public class LoginTests extends TempTestBase {

    LoginPage loginPage = new LoginPage();
    Sidebar sidebar = new ProjectsPage().getSidebar();

    @Test
    @DisplayName("Successful login as testuser")
    void loginTest() {
        loginPage.openLoginPage("")
                .setUsernameInput(ConfigHelper.getUserLogin())
                .setPasswordInput(ConfigHelper.getUserPassword())
                .clickContinueButton();
        sidebar.navigateTo(SideMenuNavItem.USER_MENU)
                .checkUsername(ConfigHelper.getUserLogin());
    }

    @Test
    @DisplayName("Successful login with localStorage (API + UI)")
    void loginWithCookieTest() {
        step("Set auth tokens to browser cookies", () -> {
            step("Open minimal content, because localstorage can be set when site is opened", () ->
                    open("/favicon.ico"));

            step("Set auths token to to browser cookies", () -> {
                WebDriverRunner.getWebDriver().manage().addCookie(
                        new Cookie("XSRF-TOKEN", ConfigHelper.getXsrfToken()));
                WebDriverRunner.getWebDriver().manage().addCookie(
                        new Cookie("ALLURE_TESTOPS_SESSION", authorizationData().getSessionToken()));
            });
        });

        step("Open main page", () ->
                open(""));

        step("Verify successful authorization", () ->
                $("img.Avatar__img").shouldHave(
                        attribute("alt", ConfigHelper.getUserLogin())));
    }
}

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
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static cloud.autotests.allure.api.helpers.AuthorizationData.getAuthorizationData;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Owner("OlegV")
@Feature("Authorization")
@Layer("ui")
public class UiAuthorizationTests extends TestBase {

    LoginPage loginPage = new LoginPage();
    Sidebar sidebar = new ProjectsPage().getSidebar();

    @Test
    @AllureId("5611")
    @Story("Authorize with valid data")
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
    @AllureId("5610")
    @Story("Authorize with valid data")
    @DisplayName("Successful login with localStorage (API + UI)")
    void loginWithCookieTest() {
        step("Set auth tokens to browser cookies", () -> {
            step("Open minimal content, because localstorage can be set when site is opened", () ->
                    open("/favicon.ico"));

            step("Set auths token to to browser cookies", () -> {
                WebDriverRunner.getWebDriver().manage().addCookie(
                        new Cookie("XSRF-TOKEN", ConfigHelper.getXsrfToken()));
                WebDriverRunner.getWebDriver().manage().addCookie(
                        new Cookie("ALLURE_TESTOPS_SESSION", getAuthorizationData().getSessionToken()));
            });
        });

        step("Open main page", () ->
                open(""));

        step("Verify successful authorization", () ->
                $("img.Avatar__img").shouldHave(
                        attribute("alt", ConfigHelper.getUserLogin())));
    }
}

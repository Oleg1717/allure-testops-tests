package cloud.autotests.allure.tests.ui;

import cloud.autotests.allure.config.ConfigHelper;
import cloud.autotests.allure.ui.data.profile.AuthoritiesType;
import cloud.autotests.allure.ui.helpers.WithLogin;
import cloud.autotests.allure.ui.helpers.allure.annotations.Layer;
import cloud.autotests.allure.ui.pages.ProfilePage;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Owner("OlegV")
@Layer("ui")
@Feature("Profile")
@Tag("profile")
public class ProfileTests extends TestBase{

    ProfilePage profilePage = new ProfilePage();

    @Test
    @WithLogin
    @Story("Check profile data")
    @DisplayName("Check user data")
    public void checkUserData() {
        profilePage.openProfilePage()
                .checkThatUserNameEquals(ConfigHelper.getMainUserLogin())
                .checkThatFullNameEquals(ConfigHelper.getMainUserFullName())
                .checkThatEmailEquals(ConfigHelper.getMainUserEmail())
                .checkThatAuthoritiesEquals(AuthoritiesType.USER.value())
                .clickApiTokenCreateButton();
    }

    @Test
    @WithLogin
    @Story("Add API token")
    @DisplayName("Add API token with valid name")
    public void addApiTokenWithValidName() {
        String tokenName = "testTokenName";
        profilePage.openProfilePage()
                .setTokenNameInput(tokenName)
                .checkThatTokenExist(tokenName);
    }
}

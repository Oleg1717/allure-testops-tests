package cloud.autotests.allure.ui.components;

import cloud.autotests.allure.ui.data.sidebar.SideMenuNavItem;
import cloud.autotests.allure.ui.data.sidebar.UserMenuItem;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Sidebar {

    private SelenideElement self = $(".SideMenu");
    private ElementsCollection sideMenuNavItems = self.$$("[aria-label]");
    private ElementsCollection userMenu = $$(".Menu__item");

    private SelenideElement favoriteProjects = $(".FavoriteProjectsAndSettings");
    private SelenideElement favoriteProjectsCloseButton = favoriteProjects.$(".FloatingMenuWithTrigger__close-button");
    private SelenideElement favoriteProjectsSeeAllLink = favoriteProjects.$(".FavoriteProjectsAndSettings__see-all-button");
    private ElementsCollection favoriteProjectsList = favoriteProjects.$$(".FavoriteProjects__list-item");

    @Step("Navigate to sidebar item `{navItem}`")
    public Sidebar navigateTo(SideMenuNavItem navItem) {
        sideMenuNavItems.find(attribute("aria-label", navItem.getDisplayedName())).click();
        return this;
    }

    @Step("Select '{menuItem}' item in user menu")
    public Sidebar selectUserMenuItem(UserMenuItem menuItem) {
        userMenu.find(text(menuItem.getDisplayedName())).click();
        return this;
    }

    @Step("Check that user is '{username}'")
    public Sidebar checkUsername(String username) {
        userMenu.find(text(UserMenuItem.SIGNED_IN_AS.getDisplayedName())).shouldHave(text(username));
        return this;
    }
}

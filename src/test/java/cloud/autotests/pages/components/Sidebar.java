package cloud.autotests.pages.components;

import cloud.autotests.data.SideMenuNavItem;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class Sidebar {

    private SelenideElement self = $(".SideMenu");
    private SelenideElement sideMenuLogo = self.$(".SideMenu__logo");
    private SelenideElement sideMenuProject = self.$(".SideMenu__project");
    private SelenideElement sideMenuUser = self.$(".SideMenu__user");
    private ElementsCollection sideMenuNav = self.$$(".SideMenu__nav-item");

    private SelenideElement projectMenuPopup = $(".tippy-content");
    private SelenideElement createProjectItem = projectMenuPopup.$(".ProjectMenu__item");
    private SelenideElement findProjectItem = projectMenuPopup.$(".Input");
    private ElementsCollection projectsList = self.$$(".ProjectMenu__menu");

    @Step("Navigate to menu item `{menuName}`")
    public void navigateTo(SideMenuNavItem menuName) {
        sideMenuNav.find(Condition.text(menuName.getDisplayedName())).click();
    }

}

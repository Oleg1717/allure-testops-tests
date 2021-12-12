package cloud.autotests.allure.ui.pages;

import cloud.autotests.allure.ui.components.Sidebar;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class ProfilePage {

    Sidebar sidebar = new Sidebar();

    ElementsCollection paneSectionList = $$(".PaneSection");

}

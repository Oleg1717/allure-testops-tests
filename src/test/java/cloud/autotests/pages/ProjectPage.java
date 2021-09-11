package cloud.autotests.pages;

import cloud.autotests.data.MenuItem;
import cloud.autotests.pages.components.Sidebar;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProjectPage {

    private Sidebar sidebar = new Sidebar();

    private ElementsCollection widgets = $$(".WidgetOld");

    private SelenideElement testCasesWidget = widgets.find(text("Test cases"));
    private SelenideElement automationWidget = widgets.find(text("Automation"));
    private SelenideElement launchesWidget = widgets.find(text("Launches"));
    private SelenideElement automationTrendWidget = widgets.find(text("Automation trend"));
    private SelenideElement muteTrendWidget = widgets.find(text("Mute trend"));

    @Step("Get Sidebar")
    public Sidebar getSidebar() {
        return sidebar;
    }

    @Step("Check that all widgets are displayed")
    public void checkThatWidgetsDisplayed() {
        testCasesWidget.should(visible);
        automationWidget.should(visible);
        launchesWidget.should(visible);
        automationTrendWidget.should(visible);
        muteTrendWidget.should(visible);
    }
}

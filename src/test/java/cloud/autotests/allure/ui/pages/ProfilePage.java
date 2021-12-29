package cloud.autotests.allure.ui.pages;

import cloud.autotests.allure.ui.data.TestUrls;
import cloud.autotests.allure.ui.data.profile.SectionName;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {

    private ElementsCollection paneSectionNamesList = $$(".PaneSection__name");
    private ElementsCollection tokensNameList = $$("AuthToken__name");

    private SelenideElement createTokenButton = $(".Button_style_primary");
    private SelenideElement tokenNameInput = $("input[name=name]");
    private SelenideElement tokenNameErrorMessage = $(".Form__error");
    private SelenideElement cancelCreatingTokenButton = $("button[name=cancel]");
    private SelenideElement submitCreatingTokenButton = $("button[name=submit]");

    private SelenideElement getSectionByName(String sectionsName) {
        return paneSectionNamesList.findBy(Condition.text(sectionsName)).parent().parent();
    }

    public ProfilePage openProfilePage() {
        open(TestUrls.USER_PROFILE.url());
        return this;
    }

    public ProfilePage checkThatUserNameEquals(String userName) {
        getSectionByName(SectionName.USERNAME.value()).shouldHave(Condition.text(userName));
        return this;
    }

    public ProfilePage checkThatFullNameEquals(String fullName) {
        getSectionByName(SectionName.FULL_NAME.value()).shouldHave(Condition.text(fullName));
        return this;
    }

    public ProfilePage checkThatEmailEquals(String email) {
        getSectionByName(SectionName.EMAIL.value()).shouldHave(Condition.text(email));
        return this;
    }

    public ProfilePage checkThatAuthoritiesEquals(String authorities) {
        getSectionByName(SectionName.AUTHORITIES.value()).shouldHave(Condition.text(authorities));
        return this;
    }

    public ProfilePage clickApiTokenCreateButton() {
        getSectionByName(SectionName.API_TOKENS.value()).$("button.Button_size_base").click();
        return this;
    }

    public ProfilePage setTokenNameInput(String tokenName) {
        tokenNameInput.val(tokenName);
        return this;
    }

    public ProfilePage checkThatTokenExist(String tokenName) {
        tokensNameList.shouldHave(CollectionCondition.texts(tokenName));
        return this;
    }
}

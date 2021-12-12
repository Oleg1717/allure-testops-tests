package cloud.autotests.allure.ui.data.profile;

public enum PaneSectionNameItem {
    USERNAME("Username"),
    FULL_NAME("Full name"),
    EMAIL("Email"),
    AUTHORITIES("Authorities"),
    API_TOKENS("API tokens!"),
    FILTER_MODE("Filter Mode");

    private final String value;

    PaneSectionNameItem(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}

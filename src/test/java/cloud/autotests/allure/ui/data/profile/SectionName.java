package cloud.autotests.allure.ui.data.profile;

public enum SectionName {
    USERNAME("Username"),
    FULL_NAME("Full name"),
    EMAIL("Email"),
    AUTHORITIES("Authorities"),
    API_TOKENS("API tokens!"),
    FILTER_MODE("Filter Mode");

    private final String value;

    SectionName(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}

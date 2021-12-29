package cloud.autotests.allure.ui.data.profile;

public enum AuthoritiesType {

    USER("USER");

    private final String value;

    AuthoritiesType (String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}

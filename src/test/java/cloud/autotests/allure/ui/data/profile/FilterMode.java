package cloud.autotests.allure.ui.data.profile;

public enum FilterMode {

    LIST_MODE("List Mode"),
    VIEW_MODE("View Mode");

    private final String value;

    FilterMode(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}

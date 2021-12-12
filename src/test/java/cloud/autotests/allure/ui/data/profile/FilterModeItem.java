package cloud.autotests.allure.ui.data.profile;

public enum FilterModeItem {

    LIST_MODE("List Mode"),
    VIEW_MODE("View Mode");

    private final String value;

    FilterModeItem(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}

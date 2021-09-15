package cloud.autotests.data;

public enum SideMenuNavItem {
    DASHBOARD("Dashboards"),
    TEST_CASES("Test cases"),
    TEST_PLANS("Test plans"),
    LAUNCHES("Launches"),
    ANALYTICS("Analytics"),
    DEFECTS("Defects"),
    JOBS("Jobs");

    private final String displayedName;

    SideMenuNavItem(String displayedName) {
        this.displayedName = displayedName;
    }

    public String getDisplayedName() {
        return displayedName;
    }

    @Override
    public String toString() {
        return displayedName;
    }
}

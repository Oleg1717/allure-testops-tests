package cloud.autotests.allure.ui.data.sidebar;

public enum SideMenuNavItem {
    TO_THE_MAIN_SCREEN("to the main screen"),
    OPEN_SUBMENU_TO_CHANGE_ACTIVE_PROJECT("Open submenu to change active project"),
    DASHBOARD("Dashboards"),
    TEST_CASES("Test cases"),
    TEST_PLANS("Test plans"),
    LAUNCHES("Launches"),
    ANALYTICS("Analytics"),
    DEFECTS("Defects"),
    JOBS("Jobs"),
    SETTINGS("Settings"),
    CREATE_A_NEW_PROJECT("Create a new project"),
    ABOUT_ALLURE_TESTOPS("About Allure TestOps"),
    USER_MENU("User menu");

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

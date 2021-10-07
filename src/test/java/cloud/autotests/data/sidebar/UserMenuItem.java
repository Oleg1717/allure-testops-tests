package cloud.autotests.data.sidebar;

public enum UserMenuItem {
    SIGNED_IN_AS("Signed in as"),
    YOUR_PROFILE("Your profile"),
    DARK_MODE("Dark mode"),
    SIGN_OUT("Sign out");

    private final String displayedName;

    UserMenuItem(String displayedName) {
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

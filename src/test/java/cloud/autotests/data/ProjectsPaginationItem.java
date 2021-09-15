package cloud.autotests.data;

public enum ProjectsPaginationItem {
    SHOW_5("Show 5", 5),
    SHOW_10("Show 10", 10),
    SHOW_25("Show 25", 25),
    SHOW_50("Show 50", 50),
    SHOW_100("Show 100", 100);

    private final String displayedName;
    private final int showCount;

    ProjectsPaginationItem(String displayedName, int showCount) {
        this.displayedName = displayedName;
        this.showCount = showCount;
    }

    public String getDisplayedName() {
        return displayedName;
    }

    public int getShowCount() {
        return showCount;
    }

    @Override
    public String toString() {
        return displayedName;
    }
}

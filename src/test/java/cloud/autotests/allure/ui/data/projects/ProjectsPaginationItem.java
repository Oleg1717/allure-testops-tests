package cloud.autotests.allure.ui.data.projects;

public enum ProjectsPaginationItem {
    SHOW_5(5),
    SHOW_10(10),
    SHOW_25(25),
    SHOW_50(50),
    SHOW_100(100);

    private final int itemsPerPage;

    ProjectsPaginationItem(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    @Override
    public String toString() {
        return String.valueOf(itemsPerPage);
    }
}

package cloud.autotests.api.data;

public enum WidgetType {

    MARKDOWN("markdown"),
    LAUNCH_LIST("launch_list"),
    ANALYTICS_GRAPH("analytics_graph");

    private final String typeName;

    WidgetType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}

package cloud.autotests.api.model.dashboards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Widget {

    @JsonProperty("dashboardId")
    int dashboardId;

    @JsonProperty("id")
    int widgetId;

    @JsonProperty("name")
    String widgetName;

    @JsonProperty("type")
    String widgetType;

}

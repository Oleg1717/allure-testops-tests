package cloud.autotests.api.model.dashboards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WidgetsInfo {

    @JsonProperty("projectId")
    int projectId;

    @JsonProperty("id")
    int dashboardId;

    @JsonProperty("name")
    int dashboardName;

    @JsonProperty("widgets")
    List<Widget> widgetsList;

}

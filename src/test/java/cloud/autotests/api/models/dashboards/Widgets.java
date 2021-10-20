package cloud.autotests.api.models.dashboards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Widgets {

    @JsonProperty("projectId")
    Integer projectId;

    @JsonProperty("id")
    Integer dashboardId;

    @JsonProperty("name")
    Integer dashboardName;

    @JsonProperty("widgets")
    List<Widget> widgetsList;

}

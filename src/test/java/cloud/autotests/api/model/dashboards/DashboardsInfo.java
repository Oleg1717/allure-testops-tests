package cloud.autotests.api.model.dashboards;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DashboardsInfo {

    @JsonProperty("content")
    List<Dashboard> dashboardsList;

    @JsonProperty("totalElements")
    int totalElements;
}

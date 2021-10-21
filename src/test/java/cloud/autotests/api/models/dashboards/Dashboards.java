package cloud.autotests.api.models.dashboards;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dashboards {

    @JsonProperty("content")
    private List<Dashboard> dashboardsList;
    @JsonProperty("totalElements")
    private Integer dashboardsCount;
}

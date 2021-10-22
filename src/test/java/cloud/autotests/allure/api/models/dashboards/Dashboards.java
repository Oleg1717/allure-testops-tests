package cloud.autotests.allure.api.models.dashboards;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dashboards {

    @JsonProperty("content")
    private List<Dashboard> dashboardsList;
    @JsonProperty("totalElements")
    private Integer dashboardsCount;
}

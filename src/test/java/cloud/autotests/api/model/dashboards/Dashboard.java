package cloud.autotests.api.model.dashboards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dashboard {

    @JsonProperty("id")
    int id;

    @JsonProperty("projectId")
    int projectId;

    @JsonProperty("name")
    String name;
}

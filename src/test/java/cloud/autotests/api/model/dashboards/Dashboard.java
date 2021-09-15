package cloud.autotests.api.model.dashboards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dashboard {

    @JsonProperty("id")
    int id;

    @JsonProperty("projectId")
    int projectId;

    @JsonProperty("name")
    String name;

    public Dashboard(int projectId, String name) {
        this.projectId = projectId;
        this.name = name;
    }
}

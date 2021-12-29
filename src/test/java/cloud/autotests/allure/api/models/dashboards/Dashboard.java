package cloud.autotests.allure.api.models.dashboards;

import cloud.autotests.allure.api.models.ElementBody;
import cloud.autotests.allure.api.models.ModelsInterface;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dashboard implements ModelsInterface {

    private Integer id;
    private Integer projectId;
    private String name;
    private String createdBy;
    @JsonProperty("widgets")
    private List<Widget> widgetsList;
}

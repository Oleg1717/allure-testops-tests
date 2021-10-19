package cloud.autotests.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {


    @JsonProperty("id")
    int id;
    @JsonProperty("name")
    String name;
    @JsonProperty("abbr")
    String abbreviation;
    @JsonProperty("isPublic")
    boolean isPublic;
    @JsonProperty("favorite")
    boolean favorite;
    @JsonProperty("description")
    String description;
    @JsonProperty("createdBy")
    String createdBy;

    public Project(String name, boolean isPublic) {
        this.name = name;
        this.isPublic = isPublic;
    }
}

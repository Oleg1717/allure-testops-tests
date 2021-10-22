package cloud.autotests.api.models.projects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

    private Integer id;
    private String name;
    @JsonProperty("abbr")
    private String abbreviation;
    private Boolean isPublic;
    private Boolean favorite;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy-MM-dd HH:mm:ss")
    private Date createdDate;
    @JsonProperty("createdBy")
    private String createdByUser;
    @JsonProperty("lastModifiedBy")
    private String lastModifiedByUser;
}

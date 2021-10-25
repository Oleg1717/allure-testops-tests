package cloud.autotests.allure.api.models.jobs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Job {

    private Integer id;
    private Integer projectId;
    private Integer buildServerId;
    private Boolean canRun;
    private String name;
    private String url;
    private String type;
    private String externalId;
    private List<JobParameter> parameters;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy-MM-dd HH:mm:ss")
    private Date createdDate;
    private String createdBy;
    private String lastModifiedBy;
}

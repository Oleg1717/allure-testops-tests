package cloud.autotests.allure.api.models.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class License {

    private String company;
    private Date activatedAt;
    private Date nextBillingAt;
    private Integer seats;
    private Boolean valid;
    private Boolean offline;
}

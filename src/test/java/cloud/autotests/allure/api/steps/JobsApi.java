package cloud.autotests.allure.api.steps;

import cloud.autotests.allure.api.models.jobs.Job;
import cloud.autotests.allure.api.requests.JobsRequests;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class JobsApi {

    @Step("Add project using API")
    public Response addJob(Job newJob) {
        return JobsRequests.addJob(newJob);
    }

    @Step("Delete job with id = {jobId} using API")
    public void deleteJob(int jobId) {
        JobsRequests.deleteJob(jobId);
    }

    @Step("Check that response status code is '{expectedCode}'")
    public void checkStatusCodeIs(Integer expectedCode, Integer actualCode) {
        assertThat(actualCode).isEqualTo(expectedCode);
    }

    @Step("Check response body")
    public void checkResponseBody(Job expectedBody, Job actualBody) {
        assertThat(actualBody)
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "lastModifiedDate", "createdBy", "lastModifiedBy")
                .isEqualTo(expectedBody);
    }


}

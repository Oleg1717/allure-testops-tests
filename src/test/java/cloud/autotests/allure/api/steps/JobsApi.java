package cloud.autotests.allure.api.steps;

import cloud.autotests.allure.api.data.JobsErrorMessage;
import cloud.autotests.allure.api.models.jobs.Job;
import cloud.autotests.allure.api.requests.JobsRequests;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class JobsApi {

    @Step("Add project using API")
    public Response addJob(Job newJob) {
        return JobsRequests.getAddJobResponse(newJob);
    }

    @Step("Delete job with id = {jobId} using API")
    public void deleteJob(int jobId) {
        JobsRequests.getDeleteJobResponse(jobId);
    }

    @Step("Check that response status code is '{expectedCode}'")
    public void checkStatusCode(Integer actualCode, Integer expectedCode) {
        assertThat(actualCode).isEqualTo(expectedCode);
    }

    @Step("Check response body")
    public void checkResponseBody(Job expectedBody, Job actualBody) {
        assertThat(actualBody)
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "lastModifiedDate", "createdBy", "lastModifiedBy")
                .isEqualTo(expectedBody);
    }

    @Step("Check that response error message is '{expectedErrorMessage}'")
    public void checkResponseErrorMessage(String actualErrorMessage, JobsErrorMessage expectedErrorMessage) {

        assertThat(actualErrorMessage).contains(expectedErrorMessage.text());
    }
}

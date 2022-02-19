package cloud.autotests.allure.tests.api;

import cloud.autotests.allure.api.data.JobsErrorMessage;
import cloud.autotests.allure.api.models.ResponseErrorBody;
import cloud.autotests.allure.api.models.jobs.Job;
import cloud.autotests.allure.api.steps.JobsApi;
import cloud.autotests.allure.api.testdata.JobData;
import cloud.autotests.allure.ui.helpers.allure.annotations.Layer;
import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Lead;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Owner("OlegV")
@Feature("Jobs")
@Layer("api")
@Tag("jobs")
public class JobsTests {

    JobData jobData = new JobData();

    @Test
    @AllureId("5605")
    @Severity(SeverityLevel.MINOR)
    @Tag("positive_only")
    @Tag("positive_negative")
    @Description("Check that that new job may be added")
    @Story("Add a job")
    @DisplayName("Add a job with max valid data")
    public void addJobWithFullValidData() {
        //given
        Job jobRequestData = jobData.getMaxJobData();
        //when
        Response response = JobsApi.addJob(jobRequestData);
        Job jobResponseData = response.as(Job.class);
        //then
        JobsApi.checkStatusCode(response.statusCode(), 200);
        JobsApi.checkResponseBody(jobRequestData, jobResponseData);
        //and
        JobsApi.deleteJob(jobResponseData.getId());
    }

    @Test
    @AllureId("5602")
    @Story("Add a job")
    @Tag("positive_negative")
    @DisplayName("Add a job with min valid data")
    public void addJobWithMinValidData() {
        //given
        Job jobRequestData = jobData.getMinJobData();
        //when
        Response response = JobsApi.addJob(jobRequestData);
        Job jobResponseData = response.as(Job.class);
        //then
        JobsApi.checkStatusCode(response.statusCode(), 404);
        JobsApi.checkResponseBody(jobRequestData, jobResponseData);
        //and
        JobsApi.deleteJob(jobResponseData.getId());
    }

    @Test
    @Tag("positive_only")
    @Tag("positive_negative")
    @AllureId("6655")
    @Story("Add a job")
    @DisplayName("Add a copy of existing job")
    public void addCopyOfExistingJob() {
        //given
        Job jobRequestData = jobData.getMaxJobData();
        Response firstJobResponse = JobsApi.addJob(jobRequestData);
        //when
        Response secondJobResponse = JobsApi.addJob(jobRequestData);
        ResponseErrorBody errorBody = secondJobResponse.as(ResponseErrorBody.class);
        //then
        JobsApi.checkStatusCode(secondJobResponse.statusCode(), 409);
        JobsApi.checkResponseErrorMessage(errorBody.getMessage(), JobsErrorMessage.COULD_NOT_EXECUTE_STATEMENT);
        //and
        JobsApi.deleteJob(firstJobResponse.as(Job.class).getId());
    }
}

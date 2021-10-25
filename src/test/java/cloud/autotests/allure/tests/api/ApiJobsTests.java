package cloud.autotests.allure.tests.api;

import cloud.autotests.allure.api.models.jobs.Job;
import cloud.autotests.allure.api.steps.JobsApi;
import cloud.autotests.allure.testdata.JobData;
import cloud.autotests.allure.ui.helpers.allure.Layer;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Owner("OlegV")
@Feature("Jobs")
@Layer("api")
public class ApiJobsTests {

    JobsApi jobsApi = new JobsApi();

    @Test
    @Story("Add a job")
    @DisplayName("Add a job with max valid data")
    public void addJobWithFullValidData() {
        //given
        Job jobRequestData = JobData.maxJobData;
        //when
        Response response = jobsApi.addJob(jobRequestData);
        Job jobResponseData = response.as(Job.class);
        //then
        jobsApi.checkStatusCodeIs(response.statusCode(), 200);
        jobsApi.checkResponseBody(jobRequestData, jobResponseData);
        //and
        jobsApi.deleteJob(jobResponseData.getId());
    }

    @Test
    @Story("Add a job")
    @DisplayName("Add a job with min valid data")
    public void addJobWithMinValidData() {
        //given
        Job jobRequestData = JobData.minJobData;
        //when
        Response response = jobsApi.addJob(jobRequestData);
        Job jobResponseData = response.as(Job.class);
        //then
        jobsApi.checkStatusCodeIs(response.statusCode(), 200);
        jobsApi.checkResponseBody(jobRequestData, jobResponseData);
        //and
        jobsApi.deleteJob(jobResponseData.getId());
    }
}

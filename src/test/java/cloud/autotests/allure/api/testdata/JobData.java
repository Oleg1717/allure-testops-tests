package cloud.autotests.allure.api.testdata;

import cloud.autotests.allure.api.models.jobs.Job;
import cloud.autotests.allure.api.models.jobs.JobParameter;
import cloud.autotests.allure.config.ConfigHelper;
import com.github.javafaker.Faker;

import java.util.Arrays;

public class JobData {

    private Faker faker = new Faker();

    public Job getMinJobData() {
        return Job.builder()
                .projectId(ConfigHelper.getProjectId())
                .name("co7-ov-regform-min-" + faker.idNumber())
                .type("jenkins")
                .build();
    }

    public Job getMaxJobData() {
        JobParameter allureNotificationsVersion = getJobParameter("ALLURE_NOTIFICATIONS_VERSION", "3.1.1");
        JobParameter threads = getJobParameter("THREADS", "5");
        return Job.builder()
                .projectId(ConfigHelper.getProjectId())
                .canRun(true)
                .buildServerId(1)
                .name("co7-ov-regform-max-" + faker.idNumber())
                .url("https://jenkins.autotests.cloud/job/c07-ov-regform/")
                .type("jenkins")
                .externalId("co7-ov-regform-max")
                .parameters(Arrays.asList(allureNotificationsVersion, threads))
                .build();
    }

    private JobParameter getJobParameter(String name, String value) {
        return JobParameter.builder()
                .name(name)
                .defaultValue(value)
                .build();
    }
}

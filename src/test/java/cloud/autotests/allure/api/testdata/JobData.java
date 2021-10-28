package cloud.autotests.allure.api.testdata;

import cloud.autotests.allure.api.models.jobs.Job;
import cloud.autotests.allure.api.models.jobs.JobParameter;
import cloud.autotests.allure.config.ConfigHelper;

import java.util.Arrays;

public class JobData {

    private static JobParameter allureNotificationVersion = JobParameter.builder()
            .name("ALLURE_NOTIFICATIONS_VERSION")
            .defaultValue("3.1.1")
            .build();

    private static JobParameter threads = JobParameter.builder()
            .name("THREADS")
            .defaultValue("5")
            .build();

    public static Job minJobData = Job.builder()
            .projectId(ConfigHelper.getProjectId())
            .name("co7-ov-regform-min")
            .type("jenkins")
            .build();

    public static Job maxJobData = Job.builder()
            .projectId(ConfigHelper.getProjectId())
            .canRun(true)
            .buildServerId(1)
            .name("co7-ov-regform-max")
            .url("https://jenkins.autotests.cloud/job/c07-ov-regform/")
            .type("jenkins")
            .externalId("co7-ov-regform-max")
            .parameters(Arrays.asList(allureNotificationVersion, threads))
            .build();
}

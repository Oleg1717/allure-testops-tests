package cloud.autotests.allure.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "file:/var/lib/jenkins/workspace/allure-testops-tests-pipeline/properties/app.properties",
        "classpath:config/app.properties"
})
public interface AppConfig extends Config {

    @Key("base.url")
    String baseUrl();

    @Key("api.uaa.path")
    String apiUaaPath();

    @Key("api.rs.path")
    String apiRsPath();

    @Key("main.user.name")
    String mainUserName();

    @Key("main.user.password")
    String mainUserPassword();

    @Key("second.user.name")
    String secondUserName();

    @Key("second.user.password")
    String secondUserPassword();

    @Key("project.id")
    Integer projectId();
}

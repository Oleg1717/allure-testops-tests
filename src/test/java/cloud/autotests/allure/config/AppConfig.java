package cloud.autotests.allure.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:/var/lib/jenkins/workspace/allure-testops-tests-pipeline/properties/app.properties"
})
public interface AppConfig extends Config {

    @Key("web.url")
    String webUrl();

    @Key("api.base.uri")
    String apiBaseUri();

    @Key("api.uaa.path")
    String apiUaaPath();

    @Key("api.rs.path")
    String apiRsPath();

    @Key("user.login")
    String userLogin();

    @Key("user.password")
    String userPassword();

    @Key("user.token")
    String userToken();

    @Key("user2.login")
    String user2Login();

    @Key("user2.password")
    String user2Password();

    @Key("xsrf.token")
    String xsrfToken();

    @Key("project.id")
    Integer projectId();
}

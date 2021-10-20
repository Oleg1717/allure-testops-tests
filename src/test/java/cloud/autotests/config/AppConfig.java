package cloud.autotests.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/app.properties"
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

    @Key("xsrf.token")
    String xsrfToken();

    @Key("project.id")
    int projectId();
}

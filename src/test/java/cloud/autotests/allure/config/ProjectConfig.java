package cloud.autotests.allure.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/local.properties",
        "classpath:config/remote.properties"
})
public interface ProjectConfig extends Config {

    @DefaultValue("chrome")
    @Key("browser.name")
    String browserName();
    @DefaultValue("91.0")
    @Key("browser.version")
    String browserVersion();
    @DefaultValue("1920x1080")
    @Key("browser.size")
    String browserSize();
    @Key("browser.mobile.view")
    String browserMobileView();
    @Key("remote.driver.url")
    String remoteDriverUrl();
    @Key("video.storage.url")
    String videoStorageUrl();
}

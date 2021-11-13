package cloud.autotests.allure.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties"
})
public interface ProjectConfig extends Config {

    @Key("browser.name")
    @DefaultValue("chrome")
    String browserName();

    @Key("browser.version")
    @DefaultValue("95.0")
    String browserVersion();

    @Key("browser.size")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("selenoid.url")
    String selenoidUrl();

    @Key("video.storage.url")
    String videoStorageUrl();
}

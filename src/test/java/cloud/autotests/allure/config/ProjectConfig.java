package cloud.autotests.allure.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:/var/lib/jenkins/workspace/allure-testops-tests-pipeline/properties/remote.properties",
        "classpath:config/local.properties"
})
public interface ProjectConfig extends Config {

    @Key("browser.name")
    String browserName();

    @Key("browser.version")
    String browserVersion();

    @Key("browser.size")
    String browserSize();

    @Key("remote.driver.url")
    String remoteDriverUrl();

    @Key("video.storage.url")
    String videoStorageUrl();
}

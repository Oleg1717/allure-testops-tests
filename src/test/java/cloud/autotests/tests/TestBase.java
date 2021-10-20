package cloud.autotests.tests;

import cloud.autotests.config.ConfigHelper;
import cloud.autotests.helpers.selenide.SelenideTestWatcher;
import cloud.autotests.helpers.DriverSettings;
import cloud.autotests.helpers.selenide.SelenideExtendedListener;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static java.lang.String.format;

@ExtendWith({AllureJunit5.class, SelenideTestWatcher.class})
public class TestBase {

    static final String PROJECT_URL = ConfigHelper.getWebUrl() + format("/project/%s/", ConfigHelper.getProjectId());

    @BeforeAll
    static void setUp() {
        DriverSettings.configure();
        RestAssured.baseURI = ConfigHelper.getApiBaseUri();
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new SelenideExtendedListener());
    }
}
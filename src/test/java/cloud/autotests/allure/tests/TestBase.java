package cloud.autotests.allure.tests;

import cloud.autotests.allure.config.ConfigHelper;
import cloud.autotests.allure.ui.helpers.SelenideTestWatcher;
import cloud.autotests.allure.ui.helpers.DriverSettings;
import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static java.lang.String.format;

@ExtendWith({AllureJunit5.class, SelenideTestWatcher.class})
public class TestBase {


    @BeforeAll
    static void setUp() {
        DriverSettings.configure();
        RestAssured.baseURI = ConfigHelper.getApiBaseUri();
    }
}
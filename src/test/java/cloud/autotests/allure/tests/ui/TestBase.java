package cloud.autotests.allure.tests.ui;

import cloud.autotests.allure.ui.helpers.DriverSettings;
import cloud.autotests.allure.ui.helpers.CustomTestWatcher;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({AllureJunit5.class, CustomTestWatcher.class})
public class TestBase {

    @BeforeAll
    static void setUp() {
        DriverSettings.configure();
    }
}
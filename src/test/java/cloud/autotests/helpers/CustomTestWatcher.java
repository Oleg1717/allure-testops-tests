package cloud.autotests.helpers;

import cloud.autotests.config.ConfigHelper;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

import static io.qameta.allure.Allure.step;

public class CustomTestWatcher implements TestWatcher {

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable throwable) {
        // do something
    }

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {
        // do something
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        step("Add attachments", () -> {
            String sessionId = DriverUtils.getSessionId();

            AllureAttachments.addScreenshotAs("Last screenshot");
            AllureAttachments.addPageSource();
            AllureAttachments.addBrowserConsoleLogs();

            Selenide.closeWebDriver();

            if (ConfigHelper.isVideoOn()) {
                AllureAttachments.addVideo(sessionId);
            }
        });
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        Selenide.closeWebDriver();
    }
}

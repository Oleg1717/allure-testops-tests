package cloud.autotests.helpers;

import com.codeborne.selenide.logevents.LogEvent;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StatusDetails;
import io.qameta.allure.selenide.AllureSelenide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.qameta.allure.util.ResultsUtils.getStatus;
import static io.qameta.allure.util.ResultsUtils.getStatusDetails;

public class ExtendedSelenideListener extends AllureSelenide {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExtendedSelenideListener.class);


    private final AllureLifecycle lifecycle;

    public ExtendedSelenideListener() {
        this(Allure.getLifecycle());
    }

    public ExtendedSelenideListener(final AllureLifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    @Override
    public void afterEvent(final LogEvent event) {
        String stepName = hidePassword(event);

        lifecycle.updateStep(stepResult -> {
            stepResult.setName(stepName);
        });

        lifecycle.getCurrentTestCaseOrStep().ifPresent(parentUuid -> {
            switch (event.getStatus()) {
                case PASS:
                    lifecycle.updateStep(step -> step.setStatus(Status.PASSED));
                    break;
                case FAIL:
                    lifecycle.updateStep(stepResult -> {
                        stepResult.setStatus(getStatus(event.getError()).orElse(Status.BROKEN));
                        stepResult.setStatusDetails(getStatusDetails(event.getError()).orElse(new StatusDetails()));
                    });
                    break;
                default:
                    LOGGER.warn("Step finished with unsupported status {}", event.getStatus());
                    break;
            }
            lifecycle.stopStep();
        });
    }

    String hidePassword(LogEvent event) {
        String element = event.getElement();
        String subject = event.getSubject();

        if(element.equals("By.name: password") && subject.contains("set value(")) {
            subject = "set value(******)";
        }

        return element + " " + subject;
    }
}

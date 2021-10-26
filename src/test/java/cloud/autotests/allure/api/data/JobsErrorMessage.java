package cloud.autotests.allure.api.data;

public enum JobsErrorMessage {

    PROJECT_ID_SHOULD_BE_SPECIFIED("project id should be specified"),
    VALIDATION_ERROR("Validation error");

    private final String text;

    JobsErrorMessage(String text) {
        this.text = text;
    }

    public String text() {
        return this.text;
    }
}

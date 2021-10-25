package cloud.autotests.allure.api.data.error_messages;

public enum JobsErrorMessage {

    PROJECT_ID_SHOULD_BE_SPECIFIED("project id should be specified"),
    VALIDATION_ERROR("Validation error");

    private final String errorName;

    JobsErrorMessage(String errorName) {
        this.errorName = errorName;
    }

    public String getErrorName() {
        return this.errorName;
    }
}

package cloud.autotests.allure.api.data.error_messages;

public enum LoginErrorMessage {

    BAD_CREDENTIALS("Bad credentials"),
    VALIDATION_ERROR("Validation error"),
    INVALID_CSRF_TOKEN("Invalid CSRF Token"),
    AN_EXPECTED_CSRF_TOKEN_CANNOT_BE_FOUND("An expected CSRF token cannot be found");

    private final String errorName;

    LoginErrorMessage(String errorName) {
        this.errorName = errorName;
    }

    public String getErrorName() {
        return this.errorName;
    }
}

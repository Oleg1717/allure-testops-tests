package cloud.autotests.allure.ui.data;

public enum FormErrorMessages {

    PROJECT_EXIST("This name is taken"),
    NAME_IS_REQUIRED("Name is required"),
    NAME_SHOULD_NOT_BE_BLANK("Name is required");

    private final String error;

    FormErrorMessages(String error) {
        this.error = error;
    }

    public String error() {
        return error;
    }
}

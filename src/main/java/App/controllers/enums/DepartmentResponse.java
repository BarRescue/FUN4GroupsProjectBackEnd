package App.controllers.enums;

public enum DepartmentResponse {
    NO_DEPARTMENT("No department has been found."),
    ERROR("A unexpected error occurred. Try again later"),
    NO_DEPARTMENTS("No departments has been found."),
    ALREADY_EXISTS("Department already exists."),
    SUCCESSFULLY_DELETED("Department successfully deleted.");

    private final String text;

    DepartmentResponse(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

package App.controllers.enums;

public enum OrderResponse {
    ERROR("A unexpected error occurred. Try again later"),
    NO_DRINKS("No drinks has been found."),
    NO_DEPARTMENT("No Department has been found."),
    NO_ROOM("No Room has been found.");

    private final String text;

    OrderResponse(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

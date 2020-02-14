package App.controllers.enums;

public enum DrinkResponse {
    NO_DRINK("No drink has been found."),
    ERROR("A unexpected error occurred. Try again later"),
    NO_DRINKS("No drinks has been found."),
    ALREADY_EXISTS("Drink already exists."),
    SUCCESSFULLY_DELETED("Drink successfully deleted.");

    private final String text;

    DrinkResponse(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

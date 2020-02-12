package App.controllers.enums;

public enum RoomResponse {
    NO_ROOMS("No rooms could be retrieved."),
    ALREADY_EXISTS("Room already exists."),
    NO_ROOM("No room could be found."),
    ERROR("A unexpected error occurred. Try again later"),
    SUCCESSFULLY_DELETED("Room successfully deleted.");

    private final String text;

    RoomResponse(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

package App.controllers.enums;

public enum RoomResponse {
    NO_ROOMS("No rooms could be retrieved");

    private final String text;

    RoomResponse(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

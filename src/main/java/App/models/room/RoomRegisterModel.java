package App.models.room;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RoomRegisterModel {
    @NotEmpty(message = "Please provide: Room Number")
    private String roomNumber;

    @NotEmpty(message = "Please provide: Room Name")
    private String roomName;
}

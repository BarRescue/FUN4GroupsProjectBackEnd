package App.controllers.room;

import App.controllers.enums.RoomResponse;
import App.entity.Room;
import App.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity getRooms() {
        List<Room> rooms = roomService.getRooms();

        if(rooms.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, RoomResponse.NO_ROOMS.toString());
        }

        return ok(rooms);
    }
}

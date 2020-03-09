package App.controllers.room;

import App.controllers.enums.AuthResponse;
import App.controllers.enums.RoomResponse;
import App.entity.Room;
import App.entity.User;
import App.models.room.RoomRegisterModel;
import App.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        List<Room> rooms = roomService.findAll();

        if(rooms.isEmpty()) {
            return new ResponseEntity<>(RoomResponse.NO_ROOMS.toString(), HttpStatus.BAD_REQUEST);
        }

        return ok(rooms);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity getRoom(@Valid @PathVariable String id) {
        Optional<Room> room = this.roomService.findById(UUID.fromString(id));

        if(!room.isPresent()) {
            return new ResponseEntity<>(RoomResponse.NO_ROOM.toString(), HttpStatus.BAD_REQUEST);
        }

        return ok(room);
    }

    @PostMapping
    public ResponseEntity createRoom(@AuthenticationPrincipal User user, @Valid @RequestBody RoomRegisterModel roomModel, HttpServletRequest request) {
        if (user.getRole().toString().equals("ADMIN")) {
            if (roomService.findRoomByRoomNumber(roomModel.getRoomNumber()).isPresent()) {
                return new ResponseEntity<>(RoomResponse.ALREADY_EXISTS.toString(), HttpStatus.BAD_REQUEST);
            }

            try {
                Room room = new Room(roomModel.getRoomNumber(), roomModel.getRoomName());
                return ok(this.roomService.createOrUpdate(room));

            } catch (Exception ex) {
                return new ResponseEntity<>(RoomResponse.ERROR.toString(), HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(AuthResponse.NO_PREMISSIONS.toString(), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteRoom(@AuthenticationPrincipal User user, @Valid @PathVariable String id, HttpServletRequest request) {
        if (user.getRole().toString().equals("ADMIN")) {
            Optional<Room> room = this.roomService.findById(UUID.fromString(id));

            if (room.isPresent()) {
                try {
                    this.roomService.delete(room.get());
                    return new ResponseEntity<>(RoomResponse.SUCCESSFULLY_DELETED.toString(), HttpStatus.OK);
                } catch (Exception ex) {
                    return new ResponseEntity<>(RoomResponse.ERROR.toString(), HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(RoomResponse.NO_ROOM.toString(), HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(AuthResponse.NO_PREMISSIONS.toString(), HttpStatus.BAD_REQUEST);
    }
}

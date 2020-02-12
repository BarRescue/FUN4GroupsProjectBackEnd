package App.service;

import App.entity.Room;
import App.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    public Optional<Room> findRoomByRoomNumber(String roomNumber) {
        return roomRepository.findRoomByRoomNumber(roomNumber);
    }

    public Room CreateOrUpdate(Room room) {
        return this.roomRepository.save(room);
    }

    public void Delete(Room room) {
        this.roomRepository.delete(room);
    }
}

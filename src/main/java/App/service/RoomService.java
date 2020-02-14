package App.service;

import App.entity.Room;
import App.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Optional<Room> findById(UUID id) {
        return roomRepository.findById(id);
    }

    public Optional<Room> findRoomByRoomNumber(String roomNumber) {
        return this.roomRepository.findRoomByRoomNumber(roomNumber);
    }

    public Room createOrUpdate(Room room) {
        return this.roomRepository.save(room);
    }

    public void delete(Room room) {
        this.roomRepository.delete(room);
    }
}

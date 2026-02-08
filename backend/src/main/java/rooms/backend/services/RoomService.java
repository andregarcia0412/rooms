package rooms.backend.services;

import org.springframework.stereotype.Service;
import rooms.backend.domain.room.CreateRoomDto;
import rooms.backend.domain.room.ReturnRoomDto;
import rooms.backend.domain.room.Room;
import rooms.backend.domain.user.ReturnUserDto;
import rooms.backend.domain.user.User;
import rooms.backend.repositories.RoomRepository;

import java.util.UUID;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final UserService userService;

    public RoomService(RoomRepository roomRepository, UserService userService) {
        this.roomRepository = roomRepository;
        this.userService = userService;
    }

    public ReturnRoomDto createRoom(CreateRoomDto createRoomDto){
        User user = userService.findById(createRoomDto.createdByUserId());
        Room room = new Room(createRoomDto.name(), createRoomDto.imagePath(), createRoomDto.targetDays(), user);
        Room savedRoom = this.roomRepository.save(room);
        return new ReturnRoomDto(savedRoom.getId(), savedRoom.getName(), savedRoom.getImagePath(), savedRoom.getTargetDays(), user);
    }

    //TODO: implement ReturnRoomDto
    public Room findById(UUID id) {
        return this.roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
    }
}

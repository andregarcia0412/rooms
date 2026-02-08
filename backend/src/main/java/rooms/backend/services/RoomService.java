package rooms.backend.services;

import org.springframework.stereotype.Service;
import rooms.backend.domain.room.CreateRoomDto;
import rooms.backend.domain.room.PatchRoomDto;
import rooms.backend.domain.room.ReturnRoomDto;
import rooms.backend.domain.room.Room;
import rooms.backend.domain.user.User;
import rooms.backend.exceptions.NotFoundException;
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

    public ReturnRoomDto findById(UUID id) {
        Room room = this.roomRepository.findById(id).orElseThrow(() -> new NotFoundException("Room not found"));

        return new ReturnRoomDto(room.getId(), room.getName(), room.getImagePath(), room.getTargetDays(), room.getCreatedBy());
    }

    public ReturnRoomDto patchRoom(UUID id, PatchRoomDto patchRoomDto) {
        Room room = this.roomRepository.findById(id).orElseThrow(() -> new NotFoundException("Room not found"));

        if(patchRoomDto.name() != null) {
            room.setName(patchRoomDto.name());
        }

        if(patchRoomDto.imagePath() != null){
            room.setImagePath(patchRoomDto.imagePath());
        }

        if(patchRoomDto.targetDays() != null) {
            room.setTargetDays(patchRoomDto.targetDays());
        }

        Room savedRoom = this.roomRepository.save(room);

        return new ReturnRoomDto(savedRoom.getId(), savedRoom.getName(), savedRoom.getImagePath(), savedRoom.getTargetDays(), savedRoom.getCreatedBy());
    }

    public String deleteRoom (UUID id) {
        roomRepository.deleteById(id);
        return "success";
    }
}

package rooms.backend.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rooms.backend.domain.room.CreateRoomDto;
import rooms.backend.domain.room.PatchRoomDto;
import rooms.backend.domain.room.ReturnRoomDto;
import rooms.backend.services.RoomService;

import java.util.UUID;

@RestController
@RequestMapping("/room")
@CrossOrigin(origins = "*")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<ReturnRoomDto> createRoom(@RequestBody @Valid CreateRoomDto createRoomDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.roomService.createRoom(createRoomDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnRoomDto> getRoom(@PathVariable UUID id) {
        return ResponseEntity.ok(this.roomService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ReturnRoomDto> patchRoom(@PathVariable UUID id, @RequestBody @Valid PatchRoomDto patchRoomDto) {
        return ResponseEntity.ok(this.roomService.patchRoom(id, patchRoomDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom (@PathVariable UUID id) {
        return ResponseEntity.ok(this.roomService.deleteRoom(id));
    }
}

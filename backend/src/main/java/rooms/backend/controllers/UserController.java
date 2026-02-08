package rooms.backend.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rooms.backend.domain.user.CreateUserDto;
import rooms.backend.domain.user.PatchUserDto;
import rooms.backend.domain.user.User;
import rooms.backend.domain.user.UserResponseDto;
import rooms.backend.services.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid CreateUserDto createUserDto) {
        User user = this.userService.createUser(createUserDto);
        return ResponseEntity.ok(new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getActiveDays(), user.getCreatedAt()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById (@PathVariable UUID id) {
        User user = this.userService.findById(id);
        return ResponseEntity.ok(new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getActiveDays(), user.getCreatedAt()));
    }

    @PatchMapping
    public ResponseEntity<UserResponseDto> patchUser (@PathVariable UUID id, @RequestBody @Valid PatchUserDto patchUserDto) {
        User user = this.userService.patchUser(id, patchUserDto);
        return ResponseEntity.ok(new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getActiveDays(), user.getCreatedAt()));
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@PathVariable UUID id) {
        this.userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}

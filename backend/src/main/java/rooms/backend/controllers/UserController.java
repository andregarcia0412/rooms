package rooms.backend.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rooms.backend.domain.user.CreateUserDto;
import rooms.backend.domain.user.PatchUserDto;
import rooms.backend.domain.user.ReturnUserDto;
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
    public ResponseEntity<ReturnUserDto> createUser(@RequestBody @Valid CreateUserDto createUserDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createUser(createUserDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnUserDto> findById (@PathVariable UUID id) {
        return ResponseEntity.ok(this.userService.findDtoById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ReturnUserDto> patchUser (@PathVariable UUID id, @RequestBody @Valid PatchUserDto patchUserDto) {
        return ResponseEntity.ok(this.userService.patchUser(id, patchUserDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        return ResponseEntity.ok(this.userService.deleteUser(id));
    }
}

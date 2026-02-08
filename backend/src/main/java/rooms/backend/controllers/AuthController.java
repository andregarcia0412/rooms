package rooms.backend.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import rooms.backend.domain.auth.LoginRequestDto;
import rooms.backend.domain.auth.LoginResponseDto;
import rooms.backend.domain.auth.RegisterRequestDto;
import rooms.backend.domain.auth.RegisterResponseDto;
import rooms.backend.services.AuthService;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
         return ResponseEntity.ok(this.authService.login(loginRequestDto));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto registerRequestDto) {
        return ResponseEntity.ok(this.authService.register(registerRequestDto));
    }
}

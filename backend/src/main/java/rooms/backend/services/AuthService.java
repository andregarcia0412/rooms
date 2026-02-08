package rooms.backend.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rooms.backend.domain.auth.LoginRequestDto;
import rooms.backend.domain.auth.LoginResponseDto;
import rooms.backend.domain.auth.RegisterRequestDto;
import rooms.backend.domain.auth.RegisterResponseDto;
import rooms.backend.domain.user.CreateUserDto;
import rooms.backend.domain.user.ReturnUserDto;
import rooms.backend.domain.user.User;
import rooms.backend.infra.security.TokenService;

@Service
public class AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthService(UserService userService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestDto.email(), loginRequestDto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        User user = (User) auth.getPrincipal();
        String accessToken = tokenService.generateAccessToken(user);

        return new LoginResponseDto(new ReturnUserDto(user.getId(), user.getName(), user.getEmail(), user.getActiveDays(), user.getCreatedAt(), user.getEntries()), accessToken);
    }

    public RegisterResponseDto register(RegisterRequestDto registerRequestDto) {
        String encryptedPassword = this.passwordEncoder.encode(registerRequestDto.password());
        CreateUserDto newUser = new CreateUserDto(registerRequestDto.name(), registerRequestDto.email(), encryptedPassword);

        ReturnUserDto savedUser = this.userService.createUser(newUser);

        return new RegisterResponseDto(savedUser);
    }
}

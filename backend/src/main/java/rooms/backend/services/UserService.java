package rooms.backend.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rooms.backend.domain.user.CreateUserDto;
import rooms.backend.domain.user.PatchUserDto;
import rooms.backend.domain.user.ReturnUserDto;
import rooms.backend.domain.user.User;
import rooms.backend.exceptions.AlreadyExistsException;
import rooms.backend.exceptions.NotFoundException;
import rooms.backend.repositories.UserRepository;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ReturnUserDto createUser(CreateUserDto createUserDto) {
        if(userRepository.findByEmail(createUserDto.email()).isPresent()) {
            throw new AlreadyExistsException("User already exists");
        }

        User user = new User(createUserDto.name(), createUserDto.email(), createUserDto.password());

        User newUser = this.userRepository.save(user);

        return new ReturnUserDto(newUser.getId(), newUser.getName(), newUser.getEmail(), newUser.getActiveDays(), newUser.getCreatedAt(), newUser.getEntries());
    }

    public ReturnUserDto findDtoById(UUID id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        return new ReturnUserDto(user.getId(), user.getName(), user.getEmail(), user.getActiveDays(), user.getCreatedAt(), user.getEntries());
    }

    public User findById(UUID id) {
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public ReturnUserDto findByEmail(String email) {
        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
        return new ReturnUserDto(user.getId(), user.getName(), user.getEmail(), user.getActiveDays(), user.getCreatedAt(), user.getEntries());
    }

    public ReturnUserDto patchUser(UUID id, PatchUserDto patchUserDto) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        if(patchUserDto.name() != null) {
            user.setName(patchUserDto.name());
        }

        if(patchUserDto.email() != null) {
            if(this.userRepository.findByEmail(patchUserDto.email()).isPresent()){
                throw new AlreadyExistsException("Email already in use");
            }
            user.setEmail(patchUserDto.email());
        }

        if(patchUserDto.password() != null) {
            user.setPassword(passwordEncoder.encode(patchUserDto.password()));
        }

        User savedUser = userRepository.save(user);

        return new ReturnUserDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getActiveDays(), savedUser.getCreatedAt(), savedUser.getEntries());
    }

    public String deleteUser(UUID id) {
        this.userRepository.deleteById(id);
        return "success";
    }
}

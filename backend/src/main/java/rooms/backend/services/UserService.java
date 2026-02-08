package rooms.backend.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rooms.backend.domain.user.CreateUserDto;
import rooms.backend.domain.user.PatchUserDto;
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

    public User createUser(CreateUserDto createUserDto) {
        if(userRepository.findByEmail(createUserDto.email()).isPresent()) {
            throw new AlreadyExistsException("User already exists");
        }

        User user = new User(createUserDto.name(), createUserDto.email(), createUserDto.password());

        return this.userRepository.save(user);
    }

    public User findById(UUID id) {
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User patchUser(UUID id, PatchUserDto patchUserDto) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        if(patchUserDto.name() != null) {
            user.setName(patchUserDto.name());
        }

        if(patchUserDto.email() != null) {
            user.setEmail(patchUserDto.email());
        }

        if(patchUserDto.password() != null) {
            user.setPassword(passwordEncoder.encode(patchUserDto.password()));
        }

        return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        this.userRepository.deleteById(id);
    }
}

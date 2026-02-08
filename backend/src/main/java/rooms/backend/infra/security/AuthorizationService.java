package rooms.backend.infra.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rooms.backend.domain.user.User;
import rooms.backend.repositories.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;

    AuthorizationService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
}

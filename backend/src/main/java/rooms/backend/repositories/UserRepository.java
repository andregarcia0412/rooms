package rooms.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rooms.backend.domain.user.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}

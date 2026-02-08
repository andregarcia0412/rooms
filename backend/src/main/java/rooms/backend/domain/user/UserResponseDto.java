package rooms.backend.domain.user;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDto (
        UUID id,
        String name,
        String email,
        int activeDays,
        LocalDateTime createdAt
) {
}

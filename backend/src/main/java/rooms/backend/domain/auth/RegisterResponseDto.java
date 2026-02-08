package rooms.backend.domain.auth;

import java.util.UUID;

public record RegisterResponseDto(
        UUID id,
        String name,
        String email
) {
}

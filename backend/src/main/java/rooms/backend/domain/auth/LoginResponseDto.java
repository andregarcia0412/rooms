package rooms.backend.domain.auth;

import java.util.UUID;

public record LoginResponseDto(
        UUID id,
        String email,
        String name,
        String accessToken
) {
}



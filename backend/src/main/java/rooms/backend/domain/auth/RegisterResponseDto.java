package rooms.backend.domain.auth;

import rooms.backend.domain.user.ReturnUserDto;

import java.util.UUID;

public record RegisterResponseDto(
        ReturnUserDto user
) {
}

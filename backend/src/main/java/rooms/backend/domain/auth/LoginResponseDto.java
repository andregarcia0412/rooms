package rooms.backend.domain.auth;

import rooms.backend.domain.user.ReturnUserDto;
public record LoginResponseDto(
        ReturnUserDto user,
        String accessToken
) {
}



package rooms.backend.domain.user;

import jakarta.validation.constraints.Email;

public record PatchUserDto(
        String name,

        @Email
        String email,

        String password
) {
}

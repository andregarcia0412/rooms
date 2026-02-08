package rooms.backend.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PatchUserDto(
        @NotBlank
        String name,

        @Email
        @NotBlank
        String email,

        @NotBlank
        String password
) {
}

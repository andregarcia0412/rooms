package rooms.backend.domain.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDto(
        @NotBlank
        String name,

        @Email
        @NotBlank
        String email,

        @NotBlank
        String password
) {
}

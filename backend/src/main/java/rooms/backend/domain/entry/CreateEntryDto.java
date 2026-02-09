package rooms.backend.domain.entry;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateEntryDto(
        @NotNull
        UUID idRoom,

        @NotNull
        UUID idUser,

        @NotBlank
        String title,

        @NotBlank
        String imagePath
) {
}

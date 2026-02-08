package rooms.backend.domain.room;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record CreateRoomDto(
        @NotBlank
        String name,

        @NotBlank
        String imagePath,

        @Positive
        Integer targetDays,

        @NotNull
        UUID createdByUserId
) {
}

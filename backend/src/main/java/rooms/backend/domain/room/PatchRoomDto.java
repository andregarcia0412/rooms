package rooms.backend.domain.room;

import jakarta.validation.constraints.Positive;

public record PatchRoomDto(
        String name,

        String imagePath,

        @Positive
        Integer targetDays
) {
}

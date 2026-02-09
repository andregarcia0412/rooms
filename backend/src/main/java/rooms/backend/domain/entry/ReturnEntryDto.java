package rooms.backend.domain.entry;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReturnEntryDto (
        UUID id,
        UUID roomId,
        UUID userId,
        String title,
        String imagePath,
        LocalDateTime createdAt
) {
    public static ReturnEntryDto fromEntity(Entry entry) {
        return new ReturnEntryDto(
                entry.getId(),
                entry.getRoom().getId(),
                entry.getUser().getId(),
                entry.getTitle(),
                entry.getImagePath(),
                entry.getCreatedAt()
        );
    }
}

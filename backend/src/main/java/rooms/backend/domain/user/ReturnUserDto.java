package rooms.backend.domain.user;

import rooms.backend.domain.entry.Entry;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ReturnUserDto(
        UUID id,
        String name,
        String email,
        int activeDays,
        LocalDateTime createdAt,
        List<Entry> entries
) {
    public static ReturnUserDto fromEntity (User user) {
        return new ReturnUserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getActiveDays(),
                user.getCreatedAt(),
                user.getEntries()
        );
    }
}

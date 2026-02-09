package rooms.backend.domain.entry;

import rooms.backend.domain.room.ReturnRoomDto;
import rooms.backend.domain.room.Room;
import rooms.backend.domain.user.ReturnUserDto;
import rooms.backend.domain.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReturnEntryDto {
    private UUID id;
    private UUID roomId;
    private UUID userId;
    private String title;
    private String imagePath;
    private LocalDateTime createdAt;

    public ReturnEntryDto(UUID id, UUID roomId, UUID userId, String title, String imagePath, LocalDateTime createdAt) {
        this.id = id;
        this.roomId = roomId;
        this.userId = userId;
        this.title = title;
        this.imagePath = imagePath;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

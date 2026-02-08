package rooms.backend.domain.room;

import jakarta.persistence.*;

import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "Room")
@Entity
public class Room {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 64)
    private String name;

    @Column(nullable = false)
    private File image;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    public Room(){};

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

    public Room(UUID id, String name, File image, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

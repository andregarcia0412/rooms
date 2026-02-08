package rooms.backend.domain.entry;

import jakarta.persistence.*;
import rooms.backend.domain.room.Room;
import rooms.backend.domain.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "Entry")
@Entity
public class Entry {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_room", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String imagePath;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

    public Entry(Room room, User user, String title, String imagePath) {
        this.room = room;
        this.user = user;
        this.title = title;
        this.imagePath = imagePath;
    }

    public Entry(UUID id, Room room, User user, String title, String imagePath, LocalDateTime createdAt) {
        this.id = id;
        this.room = room;
        this.user = user;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

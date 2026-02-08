package rooms.backend.domain.room;

import jakarta.persistence.*;
import rooms.backend.domain.entry.Entry;
import rooms.backend.domain.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    private String imagePath;

    @Column(nullable = false, name = "target_days")
    private int targetDays;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Entry> entries = new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false, name = "created_by")
    private User createdBy;

    public Room(){};

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

    public Room(String name, String imagePath, int targetDays, User createdBy) {
        this.name = name;
        this.imagePath = imagePath;
        this.targetDays = targetDays;
        this.createdBy = createdBy;
    }

    public Room(UUID id, String name, String imagePath, int targetDays, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.targetDays = targetDays;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String image) {
        this.imagePath = image;
    }

    public int getTargetDays() {
        return targetDays;
    }

    public void setTargetDays(int targetDays) {
        this.targetDays = targetDays;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}

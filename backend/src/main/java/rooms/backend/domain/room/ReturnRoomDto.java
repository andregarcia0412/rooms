package rooms.backend.domain.room;

import rooms.backend.domain.user.ReturnUserDto;
import rooms.backend.domain.user.User;

import java.util.UUID;

public class ReturnRoomDto {
    private UUID id;
    private String name;
    private String imagePath;
    private int targetDays;
    private ReturnUserDto returnUserDto;

    public ReturnRoomDto(UUID id, String name, String imagePath, int targetDays, User user) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.targetDays = targetDays;
        this.returnUserDto = new ReturnUserDto(user.getId(), user.getName(), user.getEmail(), user.getActiveDays(), user.getCreatedAt(), user.getEntries());
    }

    public ReturnRoomDto(UUID id, String name, String imagePath, int targetDays, ReturnUserDto returnUserDto) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.targetDays = targetDays;
        this.returnUserDto = returnUserDto;
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

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getTargetDays() {
        return targetDays;
    }

    public void setTargetDays(int targetDays) {
        this.targetDays = targetDays;
    }

    public ReturnUserDto getReturnUserDto() {
        return returnUserDto;
    }

    public void setReturnUserDto(ReturnUserDto returnUserDto) {
        this.returnUserDto = returnUserDto;
    }
}

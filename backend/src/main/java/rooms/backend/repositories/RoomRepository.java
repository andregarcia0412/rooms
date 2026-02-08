package rooms.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rooms.backend.domain.room.Room;

import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {

}

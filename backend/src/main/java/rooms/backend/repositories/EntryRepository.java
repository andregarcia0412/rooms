package rooms.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rooms.backend.domain.entry.Entry;

import java.util.UUID;

public interface EntryRepository extends JpaRepository<Entry, UUID> {
}

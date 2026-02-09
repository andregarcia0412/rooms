package rooms.backend.services;

import org.springframework.stereotype.Service;
import rooms.backend.domain.entry.CreateEntryDto;
import rooms.backend.domain.entry.Entry;
import rooms.backend.domain.entry.PatchEntryDto;
import rooms.backend.domain.entry.ReturnEntryDto;
import rooms.backend.domain.room.Room;
import rooms.backend.domain.user.User;
import rooms.backend.exceptions.NotFoundException;
import rooms.backend.repositories.EntryRepository;

import java.util.UUID;

@Service
public class EntryService {
    private final EntryRepository entryRepository;
    private final RoomService roomService;
    private final UserService userService;

    public EntryService(EntryRepository entryRepository, RoomService roomService, UserService userService) {
        this.entryRepository = entryRepository;
        this.roomService = roomService;
        this.userService = userService;
    }

    public ReturnEntryDto createEntry(CreateEntryDto createEntryDto) {
        Room room = this.roomService.findById(createEntryDto.idRoom());
        User user = this.userService.findById(createEntryDto.idUser());
        Entry savedEntry = this.entryRepository.save(new Entry(room, user, createEntryDto.title(), createEntryDto.imagePath()));

        return ReturnEntryDto.fromEntity(savedEntry);
    }

    public ReturnEntryDto findDtoById(UUID id) {
        Entry entry = this.findById(id);
        return ReturnEntryDto.fromEntity(entry);
    }

    public Entry findById(UUID id) {
        return this.entryRepository.findById(id).orElseThrow(() -> new NotFoundException("Entry not found"));
    }

    public ReturnEntryDto patchEntry (UUID id, PatchEntryDto patchEntryDto) {
        Entry entry = this.findById(id);

        if(patchEntryDto.title() != null) {
            entry.setTitle(patchEntryDto.title());
        }

        if(patchEntryDto.imagePath() != null) {
            entry.setImagePath(patchEntryDto.imagePath());
        }

        Entry savedEntry = this.entryRepository.save(entry);

        return ReturnEntryDto.fromEntity(savedEntry);
    }

    public String deleteEntry(UUID id) {
        this.entryRepository.deleteById(id);
        return "success";
    }

}

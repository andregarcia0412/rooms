package rooms.backend.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rooms.backend.domain.entry.CreateEntryDto;
import rooms.backend.domain.entry.ReturnEntryDto;
import rooms.backend.services.EntryService;

@RestController
@RequestMapping("/entry")
@CrossOrigin(origins = "*")
public class EntryController {
    private final EntryService entryService;
    
    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @PostMapping
    public ResponseEntity<ReturnEntryDto> createEntry(@RequestBody @Valid CreateEntryDto createEntryDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.entryService.createEntry(createEntryDto));
    }
}

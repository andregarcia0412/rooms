package rooms.backend.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rooms.backend.domain.entry.CreateEntryDto;
import rooms.backend.domain.entry.PatchEntryDto;
import rooms.backend.domain.entry.ReturnEntryDto;
import rooms.backend.services.EntryService;

import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<ReturnEntryDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(this.entryService.findDtoById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ReturnEntryDto> patchEntry(@PathVariable UUID id, @RequestBody @Valid PatchEntryDto patchEntryDto) {
        return ResponseEntity.ok(this.entryService.patchEntry(id, patchEntryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable UUID id) {
        return ResponseEntity.ok(this.entryService.deleteEntry(id));
    }
}

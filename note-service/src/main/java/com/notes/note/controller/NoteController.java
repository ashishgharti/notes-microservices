package com.notes.note.controller;

import com.notes.note.dto.CreateNoteRequest;
import com.notes.note.dto.UpdateNoteRequest;
import com.notes.note.model.Note;
import com.notes.note.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    // Create a new note
    @PostMapping
    public ResponseEntity<Note> createNote(@Valid @RequestBody CreateNoteRequest createNoteRequest) {
        Note createdNote = noteService.createNote(createNoteRequest);
        return new ResponseEntity<>(createdNote, HttpStatus.CREATED);
    }

    // Get all notes
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteService.getAllNotes();
        return ResponseEntity.ok(notes);
    }

    // Get a single note by id
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Note note = noteService.getNoteById(id);
        return ResponseEntity.ok(note);
    }

    // Update a note
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(
            @PathVariable Long id,
            @Valid @RequestBody UpdateNoteRequest updateNoteRequest) {
        Note updatedNote = noteService.updateNote(id, updateNoteRequest);
        return ResponseEntity.ok(updatedNote);
    }

    // Delete a note
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}

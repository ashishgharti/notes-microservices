package com.notes.note.service;

import com.notes.note.dto.CreateNoteRequest;
import com.notes.note.dto.UpdateNoteRequest;
import com.notes.note.model.Note;
import com.notes.note.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    // Get note by id
    public Note getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found with id: " + id));
    }

    // Update note
    public Note updateNote(Long id, UpdateNoteRequest request) {
        Note existingNote = getNoteById(id);
        existingNote.setTitle(request.getTitle());
        existingNote.setContent(request.getContent());
        return noteRepository.save(existingNote);
    }

    // Delete note
    public void deleteNote(Long id) {
        Note note = getNoteById(id);
        noteRepository.delete(note);
    }

    private String getCurrentUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    public Note createNote(CreateNoteRequest request) {
        String userEmail = getCurrentUserEmail();
        Note note = Note.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .userEmail(userEmail)  // Set user email here
                .build();
        return noteRepository.save(note);
    }

    // Similarly, update your getAllNotes() to filter by userEmail:
    public List<Note> getAllNotes() {
        String userEmail = getCurrentUserEmail();
        return noteRepository.findByUserEmailAndTrashedFalse(userEmail);
    }
}

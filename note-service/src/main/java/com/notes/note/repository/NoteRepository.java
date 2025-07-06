package com.notes.note.repository;

import com.notes.note.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByUserEmail(String userEmail);
    List<Note> findByUserEmailAndTrashedFalse(String userEmail);
    List<Note> findByUserEmailAndPinnedTrue(String userEmail);
}

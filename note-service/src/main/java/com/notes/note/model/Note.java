package com.notes.note.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 5000)
    private String content;
    private String userEmail;
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean pinned;

    private boolean archived;

    private boolean trashed;
}

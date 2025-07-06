package com.notes.note.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateNoteRequest {

    @NotBlank(message = "Title must not be blank")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @NotBlank(message = "Content must not be blank")
    private String content;

    // Optional: initial pinned or archived state
    private boolean pinned = false;
    private boolean archived = false;
}

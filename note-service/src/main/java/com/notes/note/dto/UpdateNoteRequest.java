package com.notes.note.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateNoteRequest {

    @NotBlank(message = "Title must not be blank")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @NotBlank(message = "Content must not be blank")
    private String content;

    private boolean pinned;
    private boolean archived;
}

package com.todolist.todolist.DTOs;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDto {
    private Long id;
    private String name;
    private String description;
    private boolean completed;
    private LocalDateTime creationDate;
    private LocalDateTime dueDate;
    // Se quiser, inclua apenas o id do usuário
    private Long userId;
    // getters/setters
}

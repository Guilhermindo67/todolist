package com.todolist.todolist.DTOs;

public record RegisterRequestDto(
    String name,
    String email,
    String password
) {

}

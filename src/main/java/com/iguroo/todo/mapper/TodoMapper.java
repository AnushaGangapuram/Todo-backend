package com.iguroo.todo.mapper;

import com.iguroo.todo.dto.TodoDTO;
import com.iguroo.todo.entity.Todo;

public class TodoMapper {

    // Convert DTO to Entity
    public static Todo toEntity(TodoDTO todoDto) {
        Todo todo = new Todo();
        todo.setId(todoDto.getId()); // Only if ID is provided
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        return todo;
    }

    // Convert Entity to DTO
    public static TodoDTO toDTO(Todo todo) {
        return new TodoDTO(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.isCompleted()
        );
    }
}

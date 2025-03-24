package com.iguroo.todo.service;

import com.iguroo.todo.dto.TodoDTO;
import java.util.List;

public interface TodoService {
    TodoDTO addTask(Long userId, TodoDTO todoDto);
    List<TodoDTO> getAllTasks(Long userId);
    TodoDTO getById(Long userId, Long id);
    TodoDTO updateTask(Long userId, Long id, TodoDTO todoDto);
    void deleteTask(Long userId, Long id);
    TodoDTO completeTask(Long userId, Long taskId);
    TodoDTO inCompleteTask(Long userId, Long taskId);
}

package com.iguroo.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iguroo.todo.dto.TodoDTO;

@Service
public interface TodoService {

	TodoDTO addTask(TodoDTO todoDto);

	List<TodoDTO> getAllTask();

	TodoDTO getById(Long id);

	TodoDTO updateTask(Long id, TodoDTO todoDto);

	void deleteTask(Long id);

	TodoDTO completeTask(Long taskId);

	TodoDTO inCompleteTask(Long taskId);

}

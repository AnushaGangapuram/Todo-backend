package com.iguroo.todo.service.implnt;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iguroo.todo.dto.TodoDTO;
import com.iguroo.todo.entity.Todo;
import com.iguroo.todo.exception.ResourceNotFoundException;
import com.iguroo.todo.mapper.TodoMapper;
import com.iguroo.todo.repository.TodoRepo;
import com.iguroo.todo.service.TodoService;

@Service
public class ServiceImplnt implements TodoService {

	@Autowired
	TodoRepo todorepo;

	@Override
	public TodoDTO addTask(TodoDTO todoDto) {
		Todo todo = TodoMapper.mapToEntity(todoDto);
		Todo savedTodo = todorepo.save(todo);
		return TodoMapper.mapToDto(savedTodo);
	}

	@Override
	public List<TodoDTO> getAllTask() {
		List<Todo> todos = todorepo.findAll();
		return todos.stream().map((Todo) -> TodoMapper.mapToDto(Todo)).collect(Collectors.toList());

	}

	@Override
	public TodoDTO getById(Long id) {
		Todo todo = todorepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task is not exists with given id : " + id));
		return TodoMapper.mapToDto(todo);

	}

	@Override
	public TodoDTO updateTask(Long id, TodoDTO todoDto) {
		Todo todo = todorepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task is not exist by given id" + id));

		// Todo todos = TodoMapper.mapToEntity(todoDto);
		todo.setTitle(todoDto.getTitle());
		todo.setDescription(todoDto.getDescription());
		todo.setCompleted(todoDto.isCompleted());
//		

		Todo updatedtask = todorepo.save(todo); // Ensure changes are saved in DB
		return TodoMapper.mapToDto(updatedtask);
	}

	@Override
	public void deleteTask(Long id) {
		Todo todo = todorepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task is not exists with given id: " + id));

		todorepo.deleteById(id);

	}

	@Override
	public TodoDTO completeTask(Long taskId) {
		Todo todo = todorepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task does not exist with given id: " + taskId));

        todo.setCompleted(true); // Mark as completed
        Todo updatedTask = todorepo.save(todo);
        return TodoMapper.mapToDto(updatedTask);
	}

	@Override
	public TodoDTO inCompleteTask(Long taskId) {
		Todo todo = todorepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task does not exist with given id: " + taskId));

        todo.setCompleted(false); // Mark as incomplete
        Todo updatedTask = todorepo.save(todo);
        return TodoMapper.mapToDto(updatedTask);
	}

}

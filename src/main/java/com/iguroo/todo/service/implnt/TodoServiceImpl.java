package com.iguroo.todo.service.implnt;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iguroo.todo.dto.TodoDTO;
import com.iguroo.todo.entity.Todo;
import com.iguroo.todo.entity.User;
import com.iguroo.todo.exception.ResourceNotFoundException;
import com.iguroo.todo.mapper.TodoMapper;
import com.iguroo.todo.repository.TodoRepo;
import com.iguroo.todo.repository.UserRepository;
import com.iguroo.todo.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepo todoRepository;
    private final UserRepository userRepository;

    @Autowired
    public TodoServiceImpl(TodoRepo todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TodoDTO addTask(Long userId, TodoDTO todoDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Todo todo = TodoMapper.toEntity(todoDto);
        todo.setUser(user);
        todo.setCompleted(false); // Default task status

        Todo savedTodo = todoRepository.save(todo);
        return TodoMapper.toDTO(savedTodo);
    }

    @Override
    public List<TodoDTO> getAllTasks(Long userId) {
        List<Todo> todos = todoRepository.findByUserId(userId);
        return todos.stream().map(TodoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public TodoDTO getById(Long userId, Long id) {
        Todo todo = todoRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found or does not belong to user"));
        return TodoMapper.toDTO(todo);
    }

    @Override
    public TodoDTO updateTask(Long userId, Long id, TodoDTO todoDto) {
        Todo todo = todoRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found or does not belong to user"));

        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        Todo updatedTodo = todoRepository.save(todo);
        return TodoMapper.toDTO(updatedTodo);
    }

    @Transactional
    @Override
    public void deleteTask(Long userId, Long id) {
        Todo todo = todoRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found or does not belong to user"));

        todoRepository.delete(todo);
    }

    @Override
    public TodoDTO completeTask(Long userId, Long taskId) {
        Todo todo = todoRepository.findByIdAndUserId(taskId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found or does not belong to user"));

        todo.setCompleted(true);
        Todo updatedTodo = todoRepository.save(todo);
        return TodoMapper.toDTO(updatedTodo);
    }

    @Override
    public TodoDTO inCompleteTask(Long userId, Long taskId) {
        Todo todo = todoRepository.findByIdAndUserId(taskId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found or does not belong to user"));

        todo.setCompleted(false);
        Todo updatedTodo = todoRepository.save(todo);
        return TodoMapper.toDTO(updatedTodo);
    }
}

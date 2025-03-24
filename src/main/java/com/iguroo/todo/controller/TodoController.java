package com.iguroo.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iguroo.todo.dto.TodoDTO;
import com.iguroo.todo.service.TodoService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/apis/Todo")
public class TodoController {
	
    @Autowired
    TodoService todoservice;

    // Add Task (User can only add their own task)
    @PostMapping("/{userId}/add")
    public ResponseEntity<TodoDTO> addTask(@PathVariable Long userId, @RequestBody TodoDTO todoDto) {
        TodoDTO saved = todoservice.addTask(userId, todoDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Get all tasks of logged-in user
    @GetMapping("/{userId}/getAll")
    public ResponseEntity<List<TodoDTO>> getAll(@PathVariable Long userId) {
        List<TodoDTO> tododto = todoservice.getAllTasks(userId);
        return ResponseEntity.ok(tododto);
    }

    // Get task by ID (User can only access their own task)
    @GetMapping("/{userId}/{id}")
    public ResponseEntity<TodoDTO> getById(@PathVariable Long userId, @PathVariable Long id) {
        TodoDTO tododto = todoservice.getById(userId, id);
        return ResponseEntity.ok(tododto);
    }

    // Update task (User can only update their own task)
    @PutMapping("/{userId}/{id}")
    public ResponseEntity<TodoDTO> putMethod(@PathVariable Long userId, @PathVariable Long id, @RequestBody TodoDTO todoDto) {
        TodoDTO tododto = todoservice.updateTask(userId, id, todoDto);
        return ResponseEntity.ok(tododto);
    }

    // Delete task (User can only delete their own task)
    @DeleteMapping("/{userId}/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long userId, @PathVariable Long id) {
        todoservice.deleteTask(userId, id);
        return ResponseEntity.ok("Task deleted successfully!.");
    }

    // Mark task as complete (User can only update their own task)
    @PatchMapping("/{userId}/{id}/complete")
    public ResponseEntity<TodoDTO> completeTask(@PathVariable Long userId, @PathVariable Long id) {
        TodoDTO updatedTask = todoservice.completeTask(userId, id);
        return ResponseEntity.ok(updatedTask);
    }

    // Mark task as incomplete (User can only update their own task)
    @PatchMapping("/{userId}/{id}/incomplete")
    public ResponseEntity<TodoDTO> inCompleteTask(@PathVariable Long userId, @PathVariable Long id) {
        TodoDTO updatedTask = todoservice.inCompleteTask(userId, id);
        return ResponseEntity.ok(updatedTask);
    }
}

package com.iguroo.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iguroo.todo.dto.TodoDTO;
import com.iguroo.todo.service.TodoService;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "http://localhost:5173")
@RestController

@RequestMapping("/apis/Todo")
public class TodoController {
	
	@Autowired
	TodoService todoservice;

	@PostMapping("/add")
	public ResponseEntity<TodoDTO> addTask(@RequestBody TodoDTO todoDto) {
		TodoDTO saved = todoservice.addTask(todoDto);
		return new ResponseEntity<>(saved,HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<TodoDTO>> getAll(){
		List<TodoDTO> tododto = todoservice.getAllTask();
		return ResponseEntity.ok(tododto);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TodoDTO> getById(@PathVariable Long id){
		TodoDTO tododto= todoservice.getById(id);
		return ResponseEntity.ok(tododto);	

	}
	@PutMapping("/{id}")
	public ResponseEntity<TodoDTO> putMethod(@PathVariable Long id, @RequestBody TodoDTO todoDto) {
		TodoDTO tododto= todoservice.updateTask(id, todoDto);
		return ResponseEntity.ok(tododto);
	}
	
	@DeleteMapping("/{id}")
	
	 public ResponseEntity<String> deleteTask(@PathVariable Long id){
		todoservice.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully!.");
		
	}
	
	@PatchMapping("/{id}/complete")
	
	public ResponseEntity<TodoDTO> completeTask(@PathVariable("id") Long taskId) {
	    TodoDTO updatedTask = todoservice.completeTask(taskId);
	    return ResponseEntity.ok(updatedTask);
	}
	
	@PatchMapping("/{id}/incomplete")
	public ResponseEntity<TodoDTO> inCompleteTask(@PathVariable("id") Long taskId) {
	    TodoDTO updatedTask = todoservice.inCompleteTask(taskId);
	    return ResponseEntity.ok(updatedTask);
	}

}

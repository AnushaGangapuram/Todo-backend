package com.iguroo.todo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iguroo.todo.dto.LoginDto;
import com.iguroo.todo.dto.UserDto;
import com.iguroo.todo.service.AuthService;
import com.iguroo.todo.exception.TodoApiException;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserDto regDto) {
        String response = authService.register(regDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        Map<String, Object> token = authService.login(loginDto);
        return ResponseEntity.ok().body(Map.of("token", token)); // Returning token as JSON
    }


    // Global exception handler for authentication failures
    @ExceptionHandler(TodoApiException.class)
    public ResponseEntity<String> handleTodoApiException(TodoApiException ex) {
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }
}

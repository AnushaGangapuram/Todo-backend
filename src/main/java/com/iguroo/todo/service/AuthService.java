package com.iguroo.todo.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.iguroo.todo.dto.LoginDto;
import com.iguroo.todo.dto.UserDto;

@Service
public interface AuthService {

	String register(UserDto regDto);

	Map<String, Object> login(LoginDto loginDto);

}

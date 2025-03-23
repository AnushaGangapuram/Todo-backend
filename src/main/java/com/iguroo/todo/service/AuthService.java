package com.iguroo.todo.service;

import com.iguroo.todo.dto.LoginDto;
import com.iguroo.todo.dto.UserDto;

import java.util.Map;

public interface AuthService {

    String register(UserDto userDto);

    Map<String, String> login(LoginDto loginDto);
}

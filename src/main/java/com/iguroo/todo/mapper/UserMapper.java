package com.iguroo.todo.mapper;

import com.iguroo.todo.dto.UserDto;
import com.iguroo.todo.entity.User;

public class UserMapper {
	
	public static User toEntity(UserDto userDto) {
        return new User(
            null,  // ID will be auto-generated
            userDto.getFullName(),
            userDto.getUsername(),
            userDto.getPassword(),
            userDto.getEmail(),
            null  // Roles will be handled separately
        );
    }

    public static UserDto toDto(User user) {
        return new UserDto(
            user.getFullName(),
            user.getUsername(),
            user.getPassword(),
            user.getEmail()
        );
    }

    
}

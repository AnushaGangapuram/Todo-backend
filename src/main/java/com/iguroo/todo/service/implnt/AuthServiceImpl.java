package com.iguroo.todo.service.implnt;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iguroo.todo.dto.LoginDto;
import com.iguroo.todo.dto.UserDto;
import com.iguroo.todo.entity.User;
import com.iguroo.todo.exception.TodoApiException;
import com.iguroo.todo.mapper.UserMapper;
import com.iguroo.todo.repository.UserRepository;
import com.iguroo.todo.service.AuthService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String register(@Valid @NotNull UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new TodoApiException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new TodoApiException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        User user = UserMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // Encrypt password
        userRepository.save(user);

        return "User registered successfully!";
    }

    @Override
    public String login(@Valid @NotNull LoginDto loginDto) {
        Optional<User> userOpt = userRepository.findByUsername(loginDto.getUsername());

        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("Invalid username or password!");
        }

        User user = userOpt.get();
        
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new TodoApiException(HttpStatus.UNAUTHORIZED, "Invalid username or password!");
        }

        return "Login successful!";
    }
}

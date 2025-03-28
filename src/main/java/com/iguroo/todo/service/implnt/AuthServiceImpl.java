package com.iguroo.todo.service.implnt;


import com.iguroo.todo.config.JwtUtil;
import com.iguroo.todo.dto.LoginDto;
import com.iguroo.todo.dto.UserDto;
import com.iguroo.todo.entity.User;
import com.iguroo.todo.exception.TodoApiException;
import com.iguroo.todo.repository.UserRepository;
import com.iguroo.todo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String register(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new TodoApiException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new TodoApiException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        User user = new User();
        user.setFullName(userDto.getFullName()); // ✅ Ensure fullName is set
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());

        userRepository.save(user);

        return "User registered successfully!";
    }


    @Override
    public Map<String, String> login(LoginDto loginDto) {
        Optional<User> userOpt = userRepository.findByUsername(loginDto.getUsername());

        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("Username not found");
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new TodoApiException(HttpStatus.UNAUTHORIZED, "Invalid password");
        }

        // Generate JWT Tokens
        String accessToken = jwtUtil.generateAccessToken(user.getUsername());
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

        return Map.of(
            "access_token", accessToken, 
            "refresh_token", refreshToken, 
            "username", user.getUsername()
        );
    }
}

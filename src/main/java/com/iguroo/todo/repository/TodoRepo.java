package com.iguroo.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.iguroo.todo.entity.Todo;
import java.util.List;
import java.util.Optional;

public interface TodoRepo extends JpaRepository<Todo, Long> {
    List<Todo> findByUserId(Long userId); 
    void deleteByIdAndUserId(Long id, Long userId);

    Optional<Todo> findByIdAndUserId(Long id, Long userId); // Ensure task belongs to the user
}

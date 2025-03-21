package com.iguroo.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iguroo.todo.entity.Todo;

public interface TodoRepo extends JpaRepository<Todo, Long> {

}

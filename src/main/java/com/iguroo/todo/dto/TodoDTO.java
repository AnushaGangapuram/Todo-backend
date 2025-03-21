package com.iguroo.todo.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TodoDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank (message = "Title cannot be empty")
    @Size(max = 100, message = "Title must be at most 100 characters long")
    
	@Column (nullable = false)
	private String title; 
	
	@NotBlank(message = "Description cannot be empty")
    @Size(max = 500, message = "Description must be at most 500 characters long")
	
	@Column(nullable = false)
    private String description;
	
    private boolean completed;
	

}

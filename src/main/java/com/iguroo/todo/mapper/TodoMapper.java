package com.iguroo.todo.mapper;

import com.iguroo.todo.dto.TodoDTO;
import com.iguroo.todo.entity.Todo;

public class TodoMapper {

	public static TodoDTO mapToDto(Todo todo) {
		return new TodoDTO(
				todo.getId(),
				todo.getTitle(),
				todo.getDescription(),
				todo.isCompleted()
				);
	}
		public static Todo mapToEntity(TodoDTO tododto) {
			return new  Todo(
					tododto.getId(),
					tododto.getTitle(),
					tododto.getDescription(),
					tododto.isCompleted()
					);
		}
	
	
}

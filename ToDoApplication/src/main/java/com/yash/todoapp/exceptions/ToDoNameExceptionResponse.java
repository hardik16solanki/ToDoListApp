package com.yash.todoapp.exceptions;

public class ToDoNameExceptionResponse {

	private String todoName;

	public String getTodoName() {
		return todoName;
	}

	public void setTodoName(String todoName) {
		this.todoName = todoName;
	}

	public ToDoNameExceptionResponse(String todoName) {
		super();
		this.todoName = todoName;
	}
	
	
	
}

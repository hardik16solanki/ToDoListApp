package com.yash.todoapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TodoNameException extends RuntimeException{

	public TodoNameException(String message) {
		super(message);
	}
		
}

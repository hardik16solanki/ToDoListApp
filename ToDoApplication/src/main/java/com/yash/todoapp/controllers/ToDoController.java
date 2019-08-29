package com.yash.todoapp.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.todoapp.resources.ToDoItem;
import com.yash.todoapp.services.MapValidationService;
import com.yash.todoapp.services.ToDoService;

@RestController
@RequestMapping("api/")
@CrossOrigin
public class ToDoController {

	@Autowired
	private ToDoService todoService;
	
	@Autowired
	private MapValidationService mapValidationService;
	
	@PostMapping("todo")
	public ResponseEntity<?> createNewToDoItem(@Valid @RequestBody ToDoItem item,BindingResult result,Principal principal){
		ResponseEntity<?> mapValidationErrorService = mapValidationService.MapValidationErrorService(result);
		if(mapValidationErrorService != null)
			return mapValidationErrorService;
		todoService.saveOrUpdateToDo(item,principal.getName());
		return new ResponseEntity<>(item,HttpStatus.CREATED);
	}
	
	@GetMapping("todo/{todoName}")
	public ResponseEntity<?> getTodoItemByName(@PathVariable String todoName,Principal principal){
		ToDoItem todo = todoService.findByTodoItemByName(todoName.toUpperCase(),principal.getName());
		return new ResponseEntity<ToDoItem>(todo,HttpStatus.OK);
	}
	
	@GetMapping("todos")
	public Iterable<ToDoItem> getAllToDos(Principal principal){
		return todoService.findAllToDos(principal.getName());
	}
	
	@DeleteMapping("todo/{todoName}")
	public ResponseEntity<?> deleteProject(@PathVariable String todoName,Principal principal){
		todoService.deleteToDoItemByName(todoName,principal.getName());
		return new ResponseEntity<String>("To Do Item with Name: '" + todoName + "' was deleted",HttpStatus.OK);
	}
	
	
}

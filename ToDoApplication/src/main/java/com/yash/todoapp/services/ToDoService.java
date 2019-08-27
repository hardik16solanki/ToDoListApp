package com.yash.todoapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.todoapp.exceptions.TodoNameException;
import com.yash.todoapp.repository.ToDoRepository;
import com.yash.todoapp.resources.ToDoItem;

@Service
public class ToDoService {

	@Autowired
	private ToDoRepository todoRepository;
	
	public ToDoItem saveOrUpdateToDo(ToDoItem item){
		try{
			item.setTodoName(item.getTodoName().toUpperCase());
			return todoRepository.save(item); 
		}catch(Exception e){
			throw new TodoNameException("To Do Item with Name '" + item.getTodoName() + "' already exist");
		}
	}
	
	public ToDoItem findByTodoItemByName(String name){
		ToDoItem item = todoRepository.findByTodoName(name.toUpperCase());
		if(item== null){
			throw new TodoNameException("To Do Item with Name '" + name +"' does not exists");
		}
		return item;
	}
	
	public Iterable<ToDoItem> findAllToDos(){
		return todoRepository.findAll();
	}
	
	public void deleteToDoItemByName(String name){
		ToDoItem item = todoRepository.findByTodoName(name.toUpperCase());
		if(item== null){
			throw new TodoNameException("To Do Item with Name '" + name +"' does not exists");
		}
		todoRepository.delete(item);
	}
}

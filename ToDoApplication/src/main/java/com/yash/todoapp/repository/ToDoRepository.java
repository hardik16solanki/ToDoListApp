package com.yash.todoapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yash.todoapp.resources.ToDoItem;

@Repository
public interface ToDoRepository extends CrudRepository<ToDoItem, Long>{

	Iterable<ToDoItem> findAll();
	
	ToDoItem findByTodoName(String todoName);
	
	Iterable<ToDoItem> findAllByTodoUser(String user);
	
}

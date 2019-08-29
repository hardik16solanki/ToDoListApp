package com.yash.todoapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.todoapp.exceptions.TodoNameException;
import com.yash.todoapp.repository.ToDoRepository;
import com.yash.todoapp.repository.UserRepository;
import com.yash.todoapp.resources.ToDoItem;
import com.yash.todoapp.resources.User;

@Service
public class ToDoService {

	@Autowired
	private ToDoRepository todoRepository;
	
    @Autowired
    private UserRepository userRepository;
	
	public ToDoItem saveOrUpdateToDo(ToDoItem item,String username){
		
		if(item.getId() != null){
			ToDoItem existingTodo = todoRepository.findByTodoName(item.getTodoName());
            if(existingTodo !=null &&(!existingTodo.getTodoUser().equals(username))){
                throw new TodoNameException("To Do item not found in your account");
            }else if(existingTodo == null){
                throw new TodoNameException("To do with name: '"+item.getTodoName() +"' cannot be updated because it doesn't exist");
            }
        }
		
		try{
			User user = userRepository.findByUsername(username);
			item.setUser(user);
			item.setTodoUser(user.getUsername());
			item.setTodoName(item.getTodoName().toUpperCase());
			return todoRepository.save(item); 
		}catch(Exception e){
			throw new TodoNameException("To Do Item with Name '" + item.getTodoName() + "' already exist");
		}
	}
	
	//Only want to return the project if the user looking for it is the owner
	public ToDoItem findByTodoItemByName(String name,String username){
		ToDoItem item = todoRepository.findByTodoName(name.toUpperCase());
		if(item== null){
			throw new TodoNameException("To Do Item with Name '" + name +"' does not exists");
		}
		if(!item.getTodoUser().equals(username)) {
			throw new TodoNameException("To Do Item with Name '" + name +"' does not exists in your account");
		}
		return item;
	}
	
	public Iterable<ToDoItem> findAllToDos(String username){
		return todoRepository.findAllByTodoUser(username);
	}
	
	public void deleteToDoItemByName(String name,String username){
		todoRepository.delete(findByTodoItemByName(name, username));
	}
}

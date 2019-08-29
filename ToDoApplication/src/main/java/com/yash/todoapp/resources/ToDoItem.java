package com.yash.todoapp.resources;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ToDoItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	
	@NotBlank(message="To Do Name is Required")
	@Size(min=3,max=30,message="Please use 3 to 30 characters")
	@Column(unique=true)
	private String todoName;
	
	@NotNull(message="To Do Status is required")
	private Boolean completed;
	
    @ManyToOne(fetch = FetchType.EAGER) //REMOVE REFRESH
//  @JoinColumn(name="user_id", updatable = false, nullable = false)
    @JsonIgnore
    private User user;
    
    private String todoUser;
	
    public String getTodoUser() {
		return todoUser;
	}

	public void setTodoUser(String todoUser) {
		this.todoUser = todoUser;
	}

	public ToDoItem() {
		super();
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getTodoName() {
		return todoName;
	}

	public void setTodoName(String todoName) {
		this.todoName = todoName;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
	
	
	
}

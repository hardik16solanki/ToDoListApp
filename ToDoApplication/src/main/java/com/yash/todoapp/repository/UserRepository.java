package com.yash.todoapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yash.todoapp.resources.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
    User findByUsername(String username);
    User getById(Long id);
}

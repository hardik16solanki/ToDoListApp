package com.yash.todoapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yash.todoapp.exceptions.UsernameAlreadyExistsException;
import com.yash.todoapp.repository.UserRepository;
import com.yash.todoapp.resources.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	 
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User saveUser(User newUser) {
		try {
			newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
			newUser.setConfirmPassword("");
			return userRepository.save(newUser);
		} catch (Exception e) {
			throw new UsernameAlreadyExistsException("Username '" + newUser.getUsername() +"' already exists" );
		}
		
	}
}

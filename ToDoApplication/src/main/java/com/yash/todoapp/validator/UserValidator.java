package com.yash.todoapp.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.yash.todoapp.resources.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> aclass) {
		return User.class.equals(aclass);
	}

	@Override
	public void validate(Object obj, Errors err) {
		User user = (User) obj;
		if(user.getPassword().length() < 6) {
			err.rejectValue("password","Lenght","Password must be at least 6 characters");
		}
		
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			err.rejectValue("confirmPassword","Match","Password doesn't match with confirm password");
		}
		
	}

	
}

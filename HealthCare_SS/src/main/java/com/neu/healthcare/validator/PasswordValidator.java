package com.neu.healthcare.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.healthcare.model.UserAccount;

public class PasswordValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserAccount.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserAccount userAccount = (UserAccount)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "validate.password", "Please enter New password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "validate.confirmPassword", "Please enter Confirm Password");
		
		System.out.println("I am in password validator");
		
		//
		if(!userAccount.getPassword().isEmpty() ) {
			if(!userAccount.getPassword().matches("[^<!%>/]+")) 
				errors.rejectValue("password", "password.invalid", new Object[]{userAccount.getPassword()}, "Special characters <!%>/ not allowed");
		}
		if(!userAccount.getConfirmPassword().isEmpty() ) {
			if(!userAccount.getConfirmPassword().matches("[^<!%>/]+")) 
				errors.rejectValue("confirmPassword", "confirmPassword.invalid", new Object[]{userAccount.getConfirmPassword()}, "Special characters <!%>/ not allowed");
		}
		if(!userAccount.getPassword().equals(userAccount.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "confirmPassword.invalid", new Object[]{userAccount.getConfirmPassword()}, "Passwords do not match");
		}
		
	}

}

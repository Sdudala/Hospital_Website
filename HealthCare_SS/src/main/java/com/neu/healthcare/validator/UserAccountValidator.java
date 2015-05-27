package com.neu.healthcare.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.healthcare.model.UserAccount;

public class UserAccountValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserAccount.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserAccount userAccount = (UserAccount)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "validate.userName", "Please enter UserName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "validate.password", "Please enter Password");
//		ValidationUtils.rejectIfEmpty(errors, "type", "validate.type", "Please select valid account type");
		
		//
		if(!userAccount.getUserName().isEmpty()) {
			if(!userAccount.getUserName().matches("[^<!%>/]+")) 
				errors.rejectValue("userName", "userName.invalid", new Object[]{userAccount.getUserName()}, "Special characters <!%>/ not allowed");
		}
		if(!userAccount.getPassword().isEmpty() ) {
			if(!userAccount.getPassword().matches("[^<!%>/]+")) 
				errors.rejectValue("password", "password.invalid", new Object[]{userAccount.getPassword()}, "Special characters <!%>/ not allowed");
		}
		
	}

}

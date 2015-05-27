package com.neu.healthcare.validator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.healthcare.model.Address;
import com.neu.healthcare.model.Person;
import com.neu.healthcare.model.UserAccount;

public class FullValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserAccount.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserAccount userAccount = (UserAccount)target;
		Person person = userAccount.getPerson();
		Address address = person.getAddress();
		System.out.println("In full Validator");
				
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.firstName", "validate.person.firstName", "Please enter First Name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.lastName", "validate.person.lastName", "Please enter Last Name");
		ValidationUtils.rejectIfEmpty(errors, "person.gender", "validate.person.gender", "Please select gender");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.contactNumber", "validate.person.contactNumber", "Please enter Contact Number");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.dob", "validate.person.dob", "Please enter DOB");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.address.street", "validate.person.address.street", "Please enter Street");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.address.city", "validate.person.address.city", "Please enter City");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.address.state", "validate.person.address.state", "Please enter State");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.address.country", "validate.person.address.country", "Please enter Country");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.address.zipcode", "validate.person.address.zipcode", "Please enter Zipcode");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "validate.userName", "Please enter UserName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "validate.password", "Please enter Password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "validate.confirmPassword", "Please enter Confirm Password");
		ValidationUtils.rejectIfEmpty(errors, "role", "validate.role", "Please select valid role");
		
		// Check input validity
		if(!userAccount.getUserName().isEmpty()) {
			if(!userAccount.getUserName().matches("[^<!%>/?,]+")) 
				errors.rejectValue("userName", "userName.invalid", new Object[]{userAccount.getUserName()}, "Special characters <!%>/ not allowed");
		}
		if(!userAccount.getPassword().isEmpty() ) {
			if(!userAccount.getPassword().matches("[^<!%>/?,]+")) 
				errors.rejectValue("password", "password.invalid", new Object[]{userAccount.getPassword()}, "Special characters <!%>/ not allowed");
		}
		if(!userAccount.getPassword().equals(userAccount.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "confirmPassword.invalid", new Object[]{userAccount.getConfirmPassword()}, "Passwords do not match");
		}
		
		// Check personal details
		if(!person.getFirstName().isEmpty()) {
			if(!person.getFirstName().matches("[a-zA-Z]+"))
				errors.rejectValue("person.firstName", "person.firstName.invalid", new Object[]{userAccount.getPerson().getFirstName()}, "Only letters allowed");
		}
		if(!person.getLastName().isEmpty()) {
			if(!person.getLastName().matches("[a-zA-Z]+"))
				errors.rejectValue("person.lastName", "person.lastName.invalid", new Object[]{userAccount.getPerson().getLastName()}, "Only letters allowed");
		}
		if(!person.getContactNumber().isEmpty()) {
			if(!person.getContactNumber().matches("[0-9]{3}-[0-9]{3}-[0-9]{4}"))
				errors.rejectValue("person.contactNumber", "person.contactNumber.invalid", new Object[]{userAccount.getPerson().getContactNumber()}, "Format is ddd-ddd-dddd");
		}
		
		if(!address.getStreet().isEmpty()) {
			if(!address.getStreet().matches("[a-zA-Z0-9\\s]+"))
				errors.rejectValue("person.address.street", "person.address.street.invalid", new Object[]{address.getStreet()}, "Only digits and alphabets allowed");
		}
		if(!address.getCity().isEmpty()) {
			if(!address.getCity().matches("[a-zA-Z\\s]+"))
				errors.rejectValue("person.address.city", "person.address.city.invalid", new Object[]{address.getCity()}, "Only alphabets allowed");
		}
		if(!address.getState().isEmpty()) {
			if(!address.getState().matches("[a-zA-Z\\s]+"))
				errors.rejectValue("person.address.state", "person.address.state.invalid", new Object[]{address.getState()}, "Only alphabets allowed");
		}
		if(!address.getCountry().isEmpty()) {
			if(!address.getCountry().matches("[a-zA-Z\\s]+"))
				errors.rejectValue("person.address.country", "person.address.country.invalid", new Object[]{address.getCountry()}, "Only alphabets allowed");
		}
		if(!address.getZipcode().isEmpty()) {
			if(!address.getZipcode().matches("[0-9]{5}"))
				errors.rejectValue("person.address.zipcode", "person.address.zipcode.invalid", new Object[]{address.getZipcode()}, "Valid zipcode is 5 digits");
		}
		
		// Make sure the dob is good
		if(person.getDob() != null) {
			Date now = new Date();
			LocalDate dobDate = person.getDob().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate today = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if(today.compareTo(dobDate) < 0) {
				errors.rejectValue("person.dob", "person.dob.invalid", new Object[]{person.getDob()}, "Enter valid DOB");
			}
		}
		else {
			errors.rejectValue("person.dob", "peson.dob.invalid", new Object[]{person.getDob()}, "Error Message: Enter date in format MM-dd-yyyy");
		}
				
		
	
	}

}

package com.neu.healthcare.validator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.healthcare.model.CreditCard;


public class CreditCardValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return CreditCard.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		CreditCard creditCard = (CreditCard)target;
		
		System.out.println("In creditcard Validator");
				
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "validate.firstName", "Please enter First Name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "validate.lastName", "Please enter Last Name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "number", "validate.number", "Please enter Credit Card Number");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "securityCode", "validate.securityCode", "Please enter Credit Card Security Code");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expiryDate", "validate.expiryDate", "Please enter Expiry Date");
		ValidationUtils.rejectIfEmpty(errors, "type", "validate.type", "Please select valid card type");
		
		System.out.println("In creditcard Validator");
		
		// Check input validity
			
		if(!creditCard.getFirstName().isEmpty()) {
			if(!creditCard.getFirstName().matches("[a-zA-Z]+"))
				errors.rejectValue("firstName", "firstName.invalid", new Object[]{creditCard.getFirstName()}, "Only letters allowed");
		}
		if(!creditCard.getLastName().isEmpty()) {
			if(!creditCard.getLastName().matches("[a-zA-Z]+"))
				errors.rejectValue("lastName", "lastName.invalid", new Object[]{creditCard.getLastName()}, "Only letters allowed");
		}
		if(!creditCard.getNumber().isEmpty()) {
			if(!creditCard.getNumber().matches("[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}"))
				errors.rejectValue("number", "number.invalid", new Object[]{creditCard.getNumber()}, "Format is dddd-dddd-dddd-dddd");
		}
		
		if(!creditCard.getSecurityCode().isEmpty()) {
			if(!creditCard.getSecurityCode().matches("[0-9]{3}"))
				errors.rejectValue("securityCode", "securityCode.invalid", new Object[]{creditCard.getSecurityCode()}, "Format is ddd");
		}
				
		// Make sure the date is good
		if(creditCard.getExpiryDate() != null) {
			Date now = new Date();
			LocalDate expiryDate = creditCard.getExpiryDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate today = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if(expiryDate.compareTo(today) < 0) {
				errors.rejectValue("expiryDate", "expiryDate.invalid", new Object[]{creditCard.getExpiryDate()}, "Enter valid Expiry Date");
			}
		}
		else {
			errors.rejectValue("expiryDate", "expiryDate.invalid", new Object[]{creditCard.getExpiryDate()}, "Enter date in format MM-dd-yyyy");
		}
				
		System.out.println("In creditcard Validator");
		
	}


}

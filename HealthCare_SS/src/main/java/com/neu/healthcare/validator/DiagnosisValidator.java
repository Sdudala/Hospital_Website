package com.neu.healthcare.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.healthcare.model.Appointment;

public class DiagnosisValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return Appointment.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Appointment appointment = (Appointment)target;
						
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "diagnosis", "validate.diagnosis", "Please enter Diagnosis");
		
		// Check input validity
		if(!appointment.getDiagnosis().isEmpty()) {
			if(!appointment.getDiagnosis().matches("[^<!%>/]+")) 
				errors.rejectValue("diagnosis", "diagnosis.invalid", new Object[]{appointment.getDiagnosis()}, "Special characters <!%>/ not allowed");
			if(appointment.getDiagnosis().equals("N/A"))
				errors.rejectValue("diagnosis", "diagnosis.invalid", new Object[]{appointment.getDiagnosis()}, "Please enter Diagnosis");
		}
						
	}

}

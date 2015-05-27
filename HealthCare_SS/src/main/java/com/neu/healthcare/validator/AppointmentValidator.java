package com.neu.healthcare.validator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.healthcare.model.Appointment;


public class AppointmentValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return Appointment.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Appointment appointment = (Appointment)target;
				
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "complaint", "validate.complaint", "Please enter Complaint");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "validate.date", "Please enter Date");
		ValidationUtils.rejectIfEmpty(errors, "doctorId", "validate.doctorId", "Please select a doctor");
		
		
		
		// Make sure the date is good
		
		if(appointment.getDate() != null) {
			Date now = new Date();
			LocalDate apptDate = appointment.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate today = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			System.out.println("Now checking date");
			if(apptDate.compareTo(today) < 0) {
				errors.rejectValue("date", "date.invalid", new Object[]{appointment.getDate()}, "Enter valid appointment date");
			}
		}
		else {
			errors.rejectValue("date", "date.invalid", new Object[]{appointment.getDate()}, "Enter date in format MM-dd-yyyy");
		}
		System.out.println("At the end of appointmentValidator");
						
	}

}

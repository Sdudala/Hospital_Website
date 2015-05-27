package com.neu.healthcare.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.healthcare.model.Appointment;
import com.neu.healthcare.model.VitalSigns;

public class VitalSignsValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return Appointment.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Appointment appointment = (Appointment)target;
		VitalSigns vitalSigns = appointment.getVitalSigns();
						
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vitalSigns.temperature", "validate.vitalSigns.temperature", "Please enter temperature");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vitalSigns.respRate", "validate.vitalSigns.respRate", "Please enter Resp Rate");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vitalSigns.heartRate", "validate.vitalSigns.heartRate", "Please enter Heart Rate");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vitalSigns.bloodPressure", "validate.vitalSigns.bloodPressure", "Please enter BP");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vitalSigns.weightPounds", "validate.vitalSigns.weightPounds", "Please enter Weight(lbs)");
		
		if(vitalSigns.getTemperature() != null) {
			Float temp = vitalSigns.getTemperature();
			if((temp <= 0) || (temp > 200))
				errors.rejectValue("vitalSigns.temperature", "vitalSigns.temperature.invalid", new Object[]{vitalSigns.getTemperature()}, "Valid range is 1-200");
		}
		if(vitalSigns.getRespRate() != null) {
			Float respRate = vitalSigns.getRespRate();
			if((respRate <= 0) || (respRate > 200))
				errors.rejectValue("vitalSigns.respRate", "vitalSigns.respRate.invalid", new Object[]{vitalSigns.getRespRate()}, "Valid range is 1-200");
		}
		if(vitalSigns.getHeartRate() != null) {
			Float heartRate = vitalSigns.getHeartRate();
			if((heartRate <= 0) || (heartRate > 200))
				errors.rejectValue("vitalSigns.heartRate", "vitalSigns.heartRate.invalid", new Object[]{vitalSigns.getHeartRate()}, "Valid range is 1-200");
		}
		if(vitalSigns.getBloodPressure() != null) {
			Float bp = vitalSigns.getBloodPressure();
			if((bp <= 0) || (bp > 200))
				errors.rejectValue("vitalSigns.bloodPressure", "vitalSigns.bloodPressure.invalid", new Object[]{vitalSigns.getBloodPressure()}, "Valid range is 1-200");
		}
		if(vitalSigns.getWeightPounds() != null) {
			Float wt = vitalSigns.getWeightPounds() ;
			if((wt <= 0) || (wt > 400))
				errors.rejectValue("vitalSigns.weightPounds", "vitalSigns.weightPounds.invalid", new Object[]{vitalSigns.getWeightPounds()}, "Valid range is 1-400");
		}
				
		
		
	}

}

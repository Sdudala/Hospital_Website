package com.neu.healthcare.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.neu.healthcare.model.FileUpload;

public class FileUploadValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		//just validate the FileUpload instances
		return FileUpload.class.isAssignableFrom(clazz);
	}
 
	@Override
	public void validate(Object target, Errors errors) {
 
		FileUpload file = (FileUpload)target;
		
		System.out.println("In file validator");
 
		if(file.getFile().getSize() == 0){
			System.out.println("filesize is 0");
			errors.rejectValue("file", "file.invalid", new Object[]{file.getFile()}, "Please select a file");
		}
	}

}

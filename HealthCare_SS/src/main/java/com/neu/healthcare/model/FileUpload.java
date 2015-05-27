package com.neu.healthcare.model;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	
	private MultipartFile file;

	public FileUpload() {
		
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	

}

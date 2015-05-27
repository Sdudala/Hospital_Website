package com.neu.healthcare;

import java.io.File;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.neu.healthcare.model.FileUpload;


@Controller
public class FileUploadController {
	
	/*
	 * Specify this useValidate will be injected
	 */
	@Autowired
	@Qualifier("fileUploadValidator")
	private Validator validator;
	
	/*
	 * This is to initialize webDataBinder,set its
	 * validator as we specify.
	 */
	@InitBinder
	private void initBinder (WebDataBinder binder){
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/adminFileUpload")
	public String adminFileUpload(Locale locale, Model model) {
		FileUpload file = new FileUpload();
		model.addAttribute("file", file);
		return "AdminFileUpload";
	}
	

	@RequestMapping(value = "/doFileUpload")
	public String doFileUpload(Model model, @Validated FileUpload file, BindingResult result, HttpServletRequest request) throws Exception {
		
		if (result.hasErrors()){
			model.addAttribute("file", file);
			return "AdminFileUpload";
		}
		
		MultipartFile multipartFile = file.getFile();
		String fileName="";
		 
		System.out.println("I am doing fileupload");
		if(multipartFile!=null){
			fileName = multipartFile.getOriginalFilename();
			File localFile = new File("C:\\Users\\Srikanth\\Desktop", fileName);
			//transfer the file from memory to the local file
			multipartFile.transferTo(localFile);
		}
 
		model.addAttribute("fileName", fileName);
		return "AdminFileUploadSuccess";
			
	}

}

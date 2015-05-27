package com.neu.healthcare;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neu.healthcare.dao.UserDao;
import com.neu.healthcare.model.Patient;
import com.neu.healthcare.model.Person;
import com.neu.healthcare.model.UserAccount;
import com.neu.healthcare.model.Person.GenderType;
import com.neu.healthcare.model.UserAccount.RoleType;

@Controller
public class RegistrationController {
	
	/*
	 * Specify this useValidate will be injected
	 */
	@Autowired
	@Qualifier("fullValidator")
	private Validator validator;
	
	@Autowired
	private UserDao userDao;
	
	
	/*
	 * This is to initialize webDataBinder,set its
	 * validator as we specify.
	 */
	@InitBinder
	private void initBinder (WebDataBinder binder){
		 SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		 sdf.setLenient(true);
		 binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		 binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/submitForm")
	public String submitForm(Model model, @Validated UserAccount userAccount, BindingResult result, HttpServletRequest request) {
		
		//Make sure the userName is unique
		if(result.hasErrors())
			return "Registration";
		try {
			UserAccount ua = userDao.queryUserByName(userAccount.getUserName());
			if(ua != null) {
				model.addAttribute("userAccount", userAccount);
				RoleType[] roles = {RoleType.ROLE_DOCTOR, RoleType.ROLE_NURSE, RoleType.ROLE_PATIENT, RoleType.ROLE_BILLING};  
				model.addAttribute("roles", roles);
				GenderType[] genders = GenderType.values();
				model.addAttribute("genders", genders);
				model.addAttribute("userExists", 1);
				return "Registration";
			}
				
			// If patient, add patient object
			if(userAccount.getRole() == RoleType.ROLE_PATIENT) {
				Patient patient = new Patient();
				Person person = userAccount.getPerson();
				patient.setAddress(person.getAddress());
				patient.setContactNumber(person.getContactNumber());
				patient.setDob(person.getDob());
				patient.setFirstName(person.getFirstName());
				patient.setLastName(person.getLastName());
				patient.setGender(person.getGender());
				userAccount.setPerson(patient);
			}
			userDao.addUserAccountDB(userAccount);
			
			return "SentNewUserRequest";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    	//	return "loginError";
		}
		return "Registration";
	}
	

}

package com.neu.healthcare;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.healthcare.dao.PatientDao;
import com.neu.healthcare.dao.UserDao;
import com.neu.healthcare.model.Patient;

@Controller
public class SearchController {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PatientDao patientDao;
	
	@RequestMapping(value="/searchProfile")
	public String showSearch(Model model, HttpServletRequest request/*, @Validated User user, BindingResult result*/){
		  String str = request.getParameter("submit");
		  String lastname = request.getParameter("lastname");
		  String gender = request.getParameter("gender");
		 		 
		  String state = request.getParameter("state");
		  String city = request.getParameter("city");
		  
		  String searchResults= "SearchResults";
		  
          if(str.equals("See Profile")){
        	  ArrayList<Patient> patientList;
        	  try {
        		  patientList = patientDao.queryPatientByLastname(lastname);
        		  model.addAttribute("patientList", patientList);
        	  } catch (Exception e) {
        		  // TODO Auto-generated catch block
        		  e.printStackTrace();
        	  }
        	  return searchResults;
          }
           if(str.equals("Search")){
        	   ArrayList<Patient> patientList;
        	   try {
        		   int agelo = 0;
        		   int ageup = 0;
 
        		   patientList = patientDao.queryPatientByInputs(gender, agelo, ageup, state, city);
        		   model.addAttribute("patientList", patientList);
        		   return searchResults;
        	   } catch (Exception e) {
        		   // TODO Auto-generated catch block
        		   e.printStackTrace();
        	   }
        	   return searchResults;
           }
           return searchResults;
		
	}
	
	@RequestMapping(value="/viewAddress")
	public String viewAddress(Model model, @RequestParam String personId, HttpServletRequest request){
		try {
			int personID = Integer.parseInt(personId);
			Patient patient = patientDao.getPatient(personID);
			model.addAttribute("address", patient.getAddress());
			return "ViewAddress";
		} catch (Exception e) {
 		   // TODO Auto-generated catch block
 		   e.printStackTrace();
 		  return "ViewAddress";
 	   }
	}
	
	@RequestMapping(value="/viewPatientHistory")
	public String viewPatientHistory(Model model, @RequestParam String personId, HttpServletRequest request, HttpServletResponse response){
		try {
			int personID = Integer.parseInt(personId);
			Patient patient = patientDao.getPatient(personID);
			model.addAttribute("appointmentList", patient.getAppointmentList());
			return "ViewMedicalHistory";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ViewMedicalHistory";
		}
	}
	

}

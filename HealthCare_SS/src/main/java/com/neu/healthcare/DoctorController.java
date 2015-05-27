package com.neu.healthcare;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestParam;
import com.neu.healthcare.dao.AppointmentDao;
import com.neu.healthcare.model.Appointment;
import com.neu.healthcare.model.MedicationOrder;
import com.neu.healthcare.model.UserAccount;
import com.neu.healthcare.model.Appointment.Status;

@Controller
public class DoctorController {
	
	@Autowired
	private AppointmentDao appointmentDao;
	
	/*
	 * Specify this useValidate will be injected
	 */
	@Autowired
	@Qualifier("diagnosisValidator")
	private Validator validator;
	
	/*
	 * This is to initialize webDataBinder,set its
	 * validator as we specify.
	 */
	@InitBinder
	private void initBinder (WebDataBinder binder){
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/doctor")
	public String doctorHome(Locale locale, Model model) {
		return "DoctorHome";
	}

	@RequestMapping(value = "/doctorAppointments")
	public String doctorAppointments(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserAccount curUser = (UserAccount)session.getAttribute("user");
		try {
			ArrayList<Appointment> apptList = appointmentDao.queryAppointmentsByDoc(Status.InProcess, curUser);
			model.addAttribute("apptList", apptList);
			return "DoctorAppointments";
		} catch (Exception e) {
			e.printStackTrace();
			return "DoctorHome";
		}
	}
	
	@RequestMapping(value = "/doctorDiagnosis")
	public String doctorDiagnosis(Model model, @RequestParam String apptId, HttpServletRequest request) {
		int appointmentId = Integer.parseInt(apptId);
		try {
			Appointment oldAppointment = appointmentDao.queryAppointment(appointmentId);
			model.addAttribute("apptId", appointmentId);
			Appointment appointment = new Appointment();
			model.addAttribute("appointment", appointment);
			model.addAttribute("appt", oldAppointment);
			return "DoctorDiagnosis";
		} catch (Exception e) {
			e.printStackTrace();
			return "DoctorDiagnosis";
		}
	}
	
	@RequestMapping(value = "/doctorSubmit")
	public String doctorSubmit(Model model, @Validated Appointment appointment, BindingResult result, HttpServletRequest request) {
		
		String apptId = request.getParameter("apptId");
		int appointmentId = Integer.parseInt(apptId);
		
		try {
			Appointment appointment1 = appointmentDao.queryAppointment(appointmentId);
		
			if(result.hasErrors()) {
				model.addAttribute("apptId", appointmentId);
				model.addAttribute("appointment", appointment);
				model.addAttribute("appt", appointment1);
				System.out.println("I am in error");
				return "DoctorDiagnosis";
			}
					
			appointment1.setDiagnosis(appointment.getDiagnosis());
			appointment1.setStatus(Status.Done);
			System.out.println("Appointment complaint is "+appointment1.getComplaint());
			
			int[] dosage = new int[3];
			if(!request.getParameter("dosage0").isEmpty())
				dosage[0] = Integer.parseInt(request.getParameter("dosage0"));
			if(!request.getParameter("dosage1").isEmpty())
				dosage[1] = Integer.parseInt(request.getParameter("dosage1"));
			if(!request.getParameter("dosage2").isEmpty())
				dosage[2] = Integer.parseInt(request.getParameter("dosage2"));
						
			MedicationOrder order = new MedicationOrder();
			boolean updateDone = false;
			for(int i = 0; i < 3; i++) {
			   if(dosage[i] > 0) {
				  String drug = "drug"+i;
				  String drugName = request.getParameter(drug);
				  System.out.println("Drug is "+drugName);
		    	  order.setDrug(drugName);
		    	  order.setDosage(dosage[i]);
		    	  order.setAppointment(appointment1);
		    	  appointment1.addMedication(order);
		    	  appointmentDao.updateAppointment(appointment1);
		    	  updateDone = true;
		    	}
			}
				
			if(updateDone == false)
				appointmentDao.updateAppointment(appointment1);
						
			return "DoctorApptDone";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "DoctorDiagnosis";
		
	}
	
	@RequestMapping(value = "/doctorSearch")
	public String doctorSearch(Locale locale, Model model) {
		return "DoctorSearch";
	}

}

package com.neu.healthcare;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.healthcare.dao.AppointmentDao;
import com.neu.healthcare.model.Appointment;
import com.neu.healthcare.model.Appointment.Status;

@Controller
public class NurseController {
	
	@Autowired
	private AppointmentDao appointmentDao;
	
	/*
	 * Specify this useValidate will be injected
	 */
	@Autowired
	@Qualifier("vitalSignsValidator")
	private Validator validator;
	
	/*
	 * This is to initialize webDataBinder,set its
	 * validator as we specify.
	 */
	@InitBinder
	private void initBinder (WebDataBinder binder){
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/nurse")
	public String nurseHome(Locale locale, Model model) {
		return "NurseHome";
	}
	
	@RequestMapping(value = "/nurseAppointments")
	public String nurseAppointments(Model model, HttpServletRequest request) {
		try {
			ArrayList<Appointment> apptList = appointmentDao.queryAppointments(Status.Pending);
			model.addAttribute("apptList", apptList);
			return "NurseAppointments";
		} catch (Exception e) {
			e.printStackTrace();
			return "NurseAppointments";
		}
	}
	
	@RequestMapping(value = "/nurseAddVitals")
	public String nurseAddVitals(Model model, @RequestParam String apptId, HttpServletRequest request) {
		int appointmentId = Integer.parseInt(apptId);
		try {
			Appointment appt = appointmentDao.queryAppointment(appointmentId);
			System.out.println("Appointment id is "+appt.getAppointmentId());
			System.out.println("complaint is "+appt.getComplaint());
			model.addAttribute("apptId", apptId);
			Appointment appointment = new Appointment();
			model.addAttribute("appointment", appointment);
			return "NurseVitalSignsForm";
		} catch (Exception e) {
			e.printStackTrace();
			return "NurseVitalSignsForm";
		}
	}
	
	
	@RequestMapping(value = "/nurseSubmit")
	public String nurseSubmitVitalSignsForm(Model model, @Validated Appointment appointment, BindingResult result, HttpServletRequest request) {
		
		String apptId = request.getParameter("apptId");
		int appointmentId = Integer.parseInt(apptId);
		
		if(result.hasErrors()) {
			model.addAttribute("apptId", appointmentId);
			model.addAttribute("appointment", appointment);
			System.out.println("Am i here");
			return "NurseVitalSignsForm";
		}
				
		try {
			System.out.println("Appointment id is "+appointmentId);
			Appointment appointment1 = appointmentDao.queryAppointment(appointmentId);
			appointment1.setVitalSigns(appointment.getVitalSigns());
			
			appointment1.setStatus(Status.InProcess);
			appointmentDao.updateAppointment(appointment1);
			
			return "NurseApptSent";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//	return "loginError";
		}
		model.addAttribute("apptId", appointmentId);
		model.addAttribute("appt", appointment);
		return "NurseVitalSignsForm";
	}
	
	@RequestMapping(value = "/nurseSearch")
	public String nurseSearch(Locale locale, Model model) {
		return "NurseSearch";
	}
	
	

}

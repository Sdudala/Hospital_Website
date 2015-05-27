package com.neu.healthcare;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.healthcare.dao.AppointmentDao;
import com.neu.healthcare.dao.PatientDao;
import com.neu.healthcare.dao.UserDao;
import com.neu.healthcare.model.Appointment;
import com.neu.healthcare.model.Appointment.PaymentStatus;
import com.neu.healthcare.model.Appointment.Status;
import com.neu.healthcare.model.Patient;
import com.neu.healthcare.model.UserAccount;

@Controller
public class PatientController {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AppointmentDao appointmentDao;
	
	@Autowired
	private PatientDao patientDao;
	
	/*
	 * Specify this useValidate will be injected
	 */
	@Autowired
	@Qualifier("appointmentValidator")
	private Validator validator;
	
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
	
	@RequestMapping(value = "/patient")
	public String patientHome(Locale locale, Model model) {
		return "PatientHome";
	}
	
	@RequestMapping(value = "/patientRequestAppointment")
	public String patientRequestAppointment(Model model, HttpServletRequest request) {
		try {
			ArrayList<UserAccount> doctorList = userDao.getAllDoctors();
			Appointment appointment = new Appointment();
			HttpSession session = request.getSession();
			UserAccount curUser = (UserAccount)session.getAttribute("user");
			Patient patient = patientDao.getPatient(curUser.getPerson().getPersonId());
			appointment.setPatient(patient);
			appointment.setStatus(Status.Pending);
			model.addAttribute("appointment", appointment);
			model.addAttribute("doctorList", doctorList);
			System.out.println("About to return RequestAppointment");
			return "PatientRequestAppointment";
		} catch (Exception e) {
			e.printStackTrace();
			return "PatientHome";
		}
	}
	
	@RequestMapping(value = "/patientSubmitAppt")
	public String submitAppointment(Model model, @Validated Appointment appointment, BindingResult result, HttpServletRequest request) {
		try {
			if(result.hasErrors()) {
				ArrayList<UserAccount> doctorList = userDao.getAllDoctors();
				model.addAttribute("doctorList", doctorList);
				return "PatientRequestAppointment";
			}
		
			HttpSession session = request.getSession();
			UserAccount curUser = (UserAccount)session.getAttribute("user");
			System.out.println("Doctor id is "+appointment.getDoctorId());
			UserAccount doctor = userDao.queryUserById(appointment.getDoctorId());
			Patient patient = patientDao.getPatient(curUser.getPerson().getPersonId());
			appointment.setDoctor(doctor);
			appointment.setPatient(patient);
			patient.addAppointment(appointment);
			appointmentDao.addAppointment(appointment, patient);
			return "PatientSentApptRequest";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    	//	return "loginError";
		}
		
		return "PatientHome";
		
	}
	
	@RequestMapping(value = "/patientMedicalHistory")
	public String patientMedicalHistory(Model model, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			UserAccount curUser = (UserAccount)session.getAttribute("user");
			Patient patient = patientDao.getPatient(curUser.getPerson().getPersonId());
			ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
			for(Appointment appt : patient.getAppointmentList()) {
				if(appt.getStatus() == Status.Done)
					appointmentList.add(appt);
			}
			model.addAttribute("appointmentList", appointmentList);
			return "PatientMedicalHistory";
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    	//	return "loginError";
			return "PatientHome";
		}
		
	}
	
	@RequestMapping(value="/viewVitalSign")
	public String sendMessage(Model model, @RequestParam String apptId, HttpServletRequest request){
		try {
			int appointmentId = Integer.parseInt(apptId);
			Appointment appt = appointmentDao.queryAppointment(appointmentId);
			model.addAttribute("appt", appt);
			return "ViewVitalSign";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    	//	return "loginError";
			return "PatientMedicalHistory";
		}
	}
	
	@RequestMapping(value = "/patientBills")
	public String patientBills(Model model, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			UserAccount curUser = (UserAccount)session.getAttribute("user");
			Patient patient = patientDao.getPatient(curUser.getPerson().getPersonId());
		//	ArrayList<Appointment> apptList = appointmentDao.queryAppointmentsforPaymentByPatient();
			ArrayList<Appointment> apptList = new ArrayList<Appointment>();
			for(Appointment appt : patient.getAppointmentList()) {
				if(appt.getPaymentStatus().equals(PaymentStatus.BillGenerated))
					apptList.add(appt);
			}
			model.addAttribute("apptList", apptList);
			return "PatientBills";
		}catch (Exception e) {
			e.printStackTrace();
			return "PatientHome";
		}
	}
	
	@RequestMapping(value = "/patientBillReview")
	public String patientBillReview(Model model, @RequestParam String apptId, HttpServletRequest request) {
		try {
			int appointmentId = Integer.parseInt(apptId);
			Appointment appointment = appointmentDao.queryAppointment(appointmentId);
			HttpSession session = request.getSession();
			UserAccount curUser = (UserAccount)session.getAttribute("user");
			Patient patient = patientDao.getPatient(curUser.getPerson().getPersonId());
			if(patient.getCreditCard() == null) {
				model.addAttribute("creditCardPresent", 0);
			}
			else
				model.addAttribute("creditCardPresent", 1);
			model.addAttribute("apptId", apptId);
			model.addAttribute("appointment", appointment);
			return "PatientBillReview";
		} catch (Exception e) {
			e.printStackTrace();
			return "PatientHome";
		}
	}
	
}

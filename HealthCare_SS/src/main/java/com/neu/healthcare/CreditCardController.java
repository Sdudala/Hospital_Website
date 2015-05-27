package com.neu.healthcare;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.neu.healthcare.model.Appointment;
import com.neu.healthcare.model.CreditCard;
import com.neu.healthcare.model.Patient;
import com.neu.healthcare.model.UserAccount;
import com.neu.healthcare.model.Appointment.PaymentStatus;
import com.neu.healthcare.model.CreditCard.CardType;

@Controller
public class CreditCardController {
	
	/*
	 * Specify this useValidate will be injected
	 */
	@Autowired
	@Qualifier("creditCardValidator")
	private Validator validator;
	
	@Autowired
	private AppointmentDao appointmentDao;
	
	@Autowired
	private PatientDao patientDao;
	
	
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
	
	@RequestMapping(value = "/patientPaymentAction")
	public String patientPaymentAction(Model model, @RequestParam String apptId, HttpServletRequest request) {
		int appointmentId = Integer.parseInt(apptId);
		try {
			Appointment appointment = appointmentDao.queryAppointment(appointmentId);
					
			HttpSession session = request.getSession();
			UserAccount curUser = (UserAccount)session.getAttribute("user");
			Patient patient = patientDao.getPatient(curUser.getPerson().getPersonId());
			
			System.out.println("I am in paymentAction");
			
			String value = request.getParameter("options");
			if(value.equals("new")) {
				CreditCard creditCard = new CreditCard();
				model.addAttribute("creditCard", creditCard);
				model.addAttribute("apptId", apptId);
				CardType[] cardTypes = CardType.values(); 
				model.addAttribute("cardTypes", cardTypes);
				return "PatientAddCreditCard";
			}
			else {
				System.out.println("About to do confirmation");
				model.addAttribute("bill", appointment.getTotalBill());
				model.addAttribute("apptId", apptId);
				String number = patient.getCreditCard().getNumber();
				String encodedCreditCardNumber = number.substring(number.length() - 4,number.length());
				model.addAttribute("creditCardNumber", encodedCreditCardNumber);
				return "PatientPaymentConfirmation";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "PatientBillReview";
		}
				
	}
	
	@RequestMapping(value="/patientSubmitCC")
	public String patientSubmitCC(Model model, @RequestParam String apptId, @Validated CreditCard creditCard, BindingResult result, HttpServletRequest request, HttpServletResponse response){
		
		//Make sure the userName is unique
		if(result.hasErrors()) {
			model.addAttribute("apptId", apptId);
			return "PatientAddCreditCard";
		}
		
		int appointmentId = Integer.parseInt(apptId);
		try {
			Appointment appointment = appointmentDao.queryAppointment(appointmentId);
		
			HttpSession session = request.getSession();
			UserAccount curUser = (UserAccount)session.getAttribute("user");
			Patient patient = patientDao.getPatient(curUser.getPerson().getPersonId());
			patient.setCreditCard(creditCard);
		
		
			patientDao.updatePatient(patient);
			model.addAttribute("bill", appointment.getTotalBill());
			model.addAttribute("apptId", apptId);
			String number = patient.getCreditCard().getNumber();
			String encodedCreditCardNumber = number.substring(number.length() - 4,number.length());
			model.addAttribute("creditCardNumber", encodedCreditCardNumber);
			return "PatientPaymentConfirmation";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "PatientAddCreditCard";	
		
	}
	
	@RequestMapping(value = "/patientPaymentSuccessful")
	public String patientPaymentSuccessful(Model model, @RequestParam String apptId, HttpServletRequest request) {
		int appointmentId = Integer.parseInt(apptId);
		try {
			Appointment appointment = appointmentDao.queryAppointment(appointmentId);
			appointment.setPaymentStatus(PaymentStatus.NoDue);
			appointmentDao.updateAppointment(appointment);
			return "PatientPaymentSuccessful";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//	return "loginError";
		}
		
		return "PatientPaymentSuccessful";
					
	}

}

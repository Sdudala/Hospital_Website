package com.neu.healthcare;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.neu.healthcare.dao.AppointmentDao;
import com.neu.healthcare.model.Appointment;
import com.neu.healthcare.model.Appointment.PaymentStatus;

@Controller
public class BillingController {
	
	@Autowired
	private AppointmentDao appointmentDao;
	
	@RequestMapping(value = "/billing")
	public String billing(Locale locale, Model model) {
		return "BillingHome";
	}
	
	@RequestMapping(value = "/billingAppointments")
	public String billingAppointments(Model model) {
		try {
			ArrayList<Appointment> apptList = appointmentDao.queryAppointmentsforBilling();
			model.addAttribute("apptList", apptList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "BillingAppointments";
		
	}
	
	@RequestMapping(value = "/billingGenerate")
	public String billingGenerateBill(Model model, @RequestParam String apptId, HttpServletRequest request) {
		int appointmentId = Integer.parseInt(apptId);
		try {
			Appointment appointment = appointmentDao.queryAppointment(appointmentId);
			model.addAttribute("appointment", appointment);
			appointment.calculateBill();
			appointment.setPaymentStatus(PaymentStatus.BillGenerated);
			appointmentDao.updateAppointment(appointment);
			return "BillingGenerate";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "BillingGenerate";
	}

}

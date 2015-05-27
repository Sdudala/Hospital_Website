package com.neu.healthcare.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.neu.healthcare.model.Appointment;
import com.neu.healthcare.model.MedicationOrder;
import com.neu.healthcare.model.Patient;
import com.neu.healthcare.model.UserAccount;

public class AppointmentDao extends DAO {
	
	public void addAppointment(Appointment appointment, Patient patient) throws Exception{
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Patient merged = (Patient)session.merge(patient);
			session.saveOrUpdate(merged);
			tx.commit();
			close();
			} catch (HibernateException e) {
				throw new Exception("Could not add Appointment due to "+e);
			}	
	}
	
	public ArrayList<Appointment> queryAppointments(Appointment.Status status) throws Exception {
		try {
			ArrayList<Appointment> apptList = new ArrayList<Appointment>();
			Session session = getSession();
			session.enableFilter("appointmentStatusFilter").setParameter("status", status.getValue());
			Query q = session.createQuery("from Appointment");
			apptList = (ArrayList<Appointment>)q.list();
			session.disableFilter("appointmentStatusFilter");
			close();
			return apptList;
		} catch (HibernateException e) {
			throw new Exception("queryAppointments failed due to "+e);
		}	
	}
	
	public ArrayList<Appointment> queryAppointmentsforBilling() throws Exception {
		try {
			ArrayList<Appointment> apptList = new ArrayList<Appointment>();
			Session session = getSession();
			session.enableFilter("appointmentStatusFilter").setParameter("status", Appointment.Status.Done.getValue());
			Query q = session.createQuery("from Appointment where paymentStatus != :paymentStatus");
			q.setString("paymentStatus", Appointment.PaymentStatus.NoDue.getValue());
			apptList = (ArrayList<Appointment>)q.list();
			session.disableFilter("appointmentStatusFilter");
			close();
			return apptList;
		} catch (HibernateException e) {
			throw new Exception("queryAppointmentsforBilling failed due to "+e);
		}	
	}
	
	public ArrayList<Appointment> queryAppointmentsforPayment() throws Exception {
		try {
			ArrayList<Appointment> apptList = new ArrayList<Appointment>();
			Session session = getSession();
			session.enableFilter("appointmentStatusFilter").setParameter("status", Appointment.Status.Done.getValue());
			Query q = session.createQuery("from Appointment where paymentStatus = :paymentStatus");
			q.setString("paymentStatus", Appointment.PaymentStatus.BillGenerated.getValue());
			apptList = (ArrayList<Appointment>)q.list();
			session.disableFilter("appointmentStatusFilter");
			close();
			return apptList;
		} catch (HibernateException e) {
			throw new Exception("queryAppointmentsforPayment failed due to "+e);
		}	
	}
	
	public ArrayList<Appointment> queryAppointmentsByDoc(Appointment.Status status, UserAccount ua) throws Exception{
		try {
			ArrayList<Appointment> apptList = new ArrayList<Appointment>();
			Session session = getSession();
			session.enableFilter("appointmentStatusFilter").setParameter("status", status.getValue());
			Query q = session.createQuery("from Appointment where doctorId = :docId");
			q.setInteger("docId", ua.getUserId());
			apptList = (ArrayList<Appointment>)q.list();
			session.disableFilter("appointmentStatusFilter");
			close();
			return apptList;
		} catch (HibernateException e) {
			throw new Exception("queryAppointmentsByDoc failed due to "+e);
		}	
	}
	
	public Appointment queryAppointment(int appointmentId) throws Exception {
		try {
			 Query q = getSession().createQuery("from Appointment where appointmentId = :appointmentId");
	         q.setString("appointmentId", String.valueOf(appointmentId));
	         Appointment appt = (Appointment) q.uniqueResult();
	         close();
	         return appt;
		} catch (HibernateException e) {
			throw new Exception("queryAppointment failed due to "+e);
		}	
	}
	
	public void updateAppointment(Appointment appt) throws Exception{
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			System.out.println("Complaint is "+appt.getComplaint());
			System.out.println("Temperature is "+appt.getVitalSigns().getTemperature());
			Appointment merged = (Appointment)session.merge(appt);
			System.out.println("Complaint is "+merged.getComplaint());
			session.saveOrUpdate(merged);
			tx.commit();
			close();
			
		} catch (HibernateException e) {
			throw new Exception("Could not update appointment due to "+e);
		}	
	}
	
	public void updatePrescription(List<MedicationOrder> medList) throws Exception {
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			MedicationOrder newOrder;
			for(MedicationOrder mo : medList) {
				newOrder = new MedicationOrder();
				newOrder.setAppointment(mo.getAppointment());
				newOrder.setDosage(mo.getDosage());
				newOrder.setDrug(mo.getDrug());
				newOrder.setMedicationOrderId(mo.getMedicationOrderId());
				System.out.println("Saving mo "+mo.getMedicationOrderId());
				session.save(newOrder);
				
			}
			tx.commit();
			close();
		} catch (HibernateException e) {
			throw new Exception("Could not update medication order due to "+e);
		}	
	}
	
	public void updateMedicationOrder(MedicationOrder mo) throws Exception {
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			MedicationOrder merged = (MedicationOrder)session.merge(mo);
			session.saveOrUpdate(merged);
			tx.commit();
			close();
		} catch (HibernateException e) {
			throw new Exception("Could not update medication order due to "+e);
		}	
	}

}

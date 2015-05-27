package com.neu.healthcare.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.neu.healthcare.model.Patient;


public class PatientDao extends DAO {
	
	public Patient getPatient(int patientId) throws Exception {
		try {
			Query q = getSession().createQuery("from Patient where personId = :patientId");
			q.setInteger("patientId", patientId);
			Patient patient = (Patient) q.uniqueResult();
			close();
			return patient;
		} catch (HibernateException e) {
			throw new Exception("Could not retrieve patient with id due to "+e);
		}	
	}
	
	public ArrayList<Patient> queryPatientByLastname(String lastname) throws Exception {
		try {
			ArrayList<Patient> patientList = new ArrayList<Patient>();
			Query q = getSession().createQuery("from Patient where lastName = :lastname");
			q.setString("lastname", lastname);
			patientList = (ArrayList<Patient>)q.list();
			close();
			return patientList;
		} catch (HibernateException e) {
			throw new Exception("Could not retrieve patient using lastname due to "+e);
		}	
	}
	
	public ArrayList<Patient> queryPatientByInputs(String gender, int agelo, int ageup, String state, String city) throws Exception {
		try {
			ArrayList<Patient> patientList = new ArrayList<Patient>();
			Query q;
			Session session = getSession();
			if(!gender.equals("All")) {
				session.enableFilter("patientGenderFilter").setParameter("genderParam", gender);
			}
						
		    if(state.isEmpty() && city.isEmpty()) {
		    	q = session.createQuery("from Patient");
		    }
		    else if(!state.isEmpty() && !city.isEmpty()) {
		    	q = session.createQuery("from Patient where address.state = :state and address.city = :city");
		    }
		    else if(state.isEmpty()) {
		    	q = session.createQuery("from Patient where address.city = :city");
		    }
		    else {
		    	q = session.createQuery("from Patient where address.state = :state");
		    }
			
		    if(!city.isEmpty())
				q.setString("city", city);
			if(!state.isEmpty())
				q.setString("state", state);
			
			patientList = (ArrayList<Patient>)q.list();
			
			session.disableFilter("patientGenderFilter");
			close();
			return patientList;
			
		} catch (HibernateException e) {
			throw new Exception("Could not retrieve patients due to "+e);
		}	
	}
	
	public void updatePatient(Patient patient) throws Exception{
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			System.out.println("Patient is "+patient.getFirstName());
			Patient merged = (Patient)session.merge(patient);
			session.saveOrUpdate(merged);
			tx.commit();
			close();
			
		} catch (HibernateException e) {
			throw new Exception("Could not update patient due to "+e);
		}	
	}

}

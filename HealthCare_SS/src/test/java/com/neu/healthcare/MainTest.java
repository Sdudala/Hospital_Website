package com.neu.healthcare;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.neu.healthcare.dao.HibernateUtil;
import com.neu.healthcare.model.Address;
import com.neu.healthcare.model.Person;
import com.neu.healthcare.model.Person.GenderType;
import com.neu.healthcare.model.UserAccount;
import com.neu.healthcare.model.UserAccount.RoleType;

public class MainTest {
	
	public static void main(String[] args)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Transaction t = session.beginTransaction();
		
		UserAccount ua = new UserAccount();
		Person p = new Person();
		Address address = new Address();
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); 
		try {
			date = dateFormat.parse("02/13/1950");
		} catch(ParseException e) {
			System.out.println("Enter date correctly");
		}
		
		address.setStreet("Royal Crest");
		address.setCity("Boston");
		address.setState("MA");
		address.setCountry("USA");
		address.setZipcode("01752");
		
		p.setFirstName("Jim");
		p.setLastName("Kelly");
		p.setDob(date);
		p.setContactNumber("1234567890");
		p.setAddress(address);
		p.setGender(GenderType.Male);
		
		ua.setUserName("admin");
		ua.setPassword("123");
		ua.setPerson(p);
		ua.setRole(RoleType.ROLE_ADMIN);
		ua.setEnabled(true);
		
		session.save(ua);
				
		t.commit();
		
    	session.close();
		
		System.out.println("Success");
		
		
	}

}

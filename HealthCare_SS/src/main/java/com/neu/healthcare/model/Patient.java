package com.neu.healthcare.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

@Entity  
@AttributeOverrides({  
    @AttributeOverride(name="firstName", column=@Column(name="FIRSTNAME")),  
    @AttributeOverride(name="lastName", column=@Column(name="LASTNAME")), 
    @AttributeOverride(name="contactNumber", column=@Column(name="CONTACT_NUMBER")),  
    @AttributeOverride(name="dob", column=@Column(name="BIRTH_DATE")),
    @AttributeOverride(name="gender", column=@Column(name="GENDER"))  
})  

@FilterDef(name="patientGenderFilter", parameters=@ParamDef(name="genderParam", type="string"))
@Filters( {
    @Filter(name="patientGenderFilter", condition=":genderParam = GENDER")
} )
public class Patient extends Person {
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="patient", cascade=CascadeType.ALL)
	private List<Appointment> appointmentList;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private CreditCard creditCard;

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Appointment> getAppointmentList() {
		return appointmentList;
	}

	public void setAppointmentList(List<Appointment> appointmentList) {
		this.appointmentList = appointmentList;
	}
	
	public void addAppointment(Appointment appointment) {
		appointmentList.add(appointment);
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
		

}

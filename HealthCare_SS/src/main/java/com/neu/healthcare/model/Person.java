package com.neu.healthcare.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)  
public class Person implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="PERSONID", unique=true, nullable=false)
	private int personId;
	
	@Column(name = "FIRSTNAME", nullable=false, length=100)
	private String firstName;
	
	@Column(name = "LASTNAME", nullable=false, length=100)
	private String lastName;
	
	@Column(name = "CONTACT_NUMBER", nullable=false)
	private String contactNumber;
	
	@Column(name = "BIRTH_DATE", nullable=false)
	private Date dob;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Address address;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "GENDER")
	private GenderType gender;
	
	public enum GenderType {
		Male("Male"),
        Female("Female");
             
        private String value;
        private GenderType(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
		
	}
	
	public Person() {
		
	}
	
	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

}

package com.neu.healthcare.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Address implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ADDRESS_ID", unique=true, nullable=false)
	private int addressID;
	
	@Column(name = "STREET", nullable=false, length=50)
	@NotNull
    @Size(min = 1, max = 50, message="street must be between 1 and 50 characters")
	private String street;
	
	@Column(name = "CITY", nullable=false, length=50)
	@NotNull
    @Size(min = 1, max = 50, message="city must be between 1 and 50 characters")
	private String city;
	
	@Column(name = "STATE", nullable=false, length=50)
	@NotNull
    @Size(min = 1, max = 50, message="state must be between 1 and 50 characters")
	private String state;
	
	@Column(name = "ZIPCODE", nullable=false)
	@NotNull
	private String zipcode;
	
	@Column(name = "COUNTRY", nullable=false, length=50)
	@NotNull
    @Size(min = 1, max = 50, message="country must be between 1 and 50 characters")
	private String country;

	public Address() {
		
	}

	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}

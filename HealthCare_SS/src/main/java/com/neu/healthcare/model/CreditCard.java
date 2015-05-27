package com.neu.healthcare.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class CreditCard implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CREDITCARD_ID", unique=true, nullable=false)
	private int creditCardId;
	
	@Column(name = "FIRSTNAME", nullable=false, length=50)
	@NotNull
    @Size(min = 1, max = 50, message="firstName must be between 1 and 50 characters")
	private String firstName;
	
	@Column(name = "LASTNAME", nullable=false, length=50)
	@NotNull
    @Size(min = 1, max = 50, message="lastName must be between 1 and 50 characters")
	private String lastName;
	
	@Column(name = "NUMBER", nullable=false, length=200)
	@NotNull
   	private String number;
	
	@Column(name = "SECURITYCODE", nullable=false, length=200)
	@NotNull
	private String securityCode;
	
	@Column(name = "EXPIRY_DATE", nullable=false, length=200)
	@NotNull
   	private Date expiryDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "CARDTYPE")
	private CardType type;
	
	public enum CardType {
		VISA("VISA"),
		MASTERCARD("MASTERCARD"),
		DISCOVER("DISCOVER"),
        AMEX("AMEX");
             
        private String value;
        private CardType(String value){
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

	public CreditCard() {
		
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getCreditCardId() {
		return creditCardId;
	}

	public void setCreditCardId(int creditCardId) {
		this.creditCardId = creditCardId;
	}

	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}
	
	

}

package com.neu.healthcare.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class UserAccount implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="USERID", unique=true, nullable=false)
	private int userId;
	
	@Column(name = "USERNAME", nullable=false, length=50)
	@NotNull
    @Size(min = 1, max = 50, message="userName must be between 1 and 50 characters")
	private String userName;
	
	@Column(name = "PASSWORD", nullable=false, length=50)
	@NotNull
    @Size(min = 1, max = 50, message="password must be between 1 and 50 characters")
	private String password;
	
	@Transient
	private String confirmPassword;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	//@PrimaryKeyJoinColumn
	private Person person;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ROLE")
	private RoleType role;
	
	@Column(name = "ENABLED")
	private boolean enabled = false;
	
	public enum RoleType {
		ROLE_ADMIN("ROLE_ADMIN"),
        ROLE_DOCTOR("ROLE_DOCTOR"),
        ROLE_NURSE("ROLE_NURSE"),
        ROLE_PATIENT("ROLE_PATIENT"),
        ROLE_BILLING("ROLE_BILLING");
        
        
        private String value;
        private RoleType(String value){
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
	
	public UserAccount() {
		
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

		
	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	    public String toString() {
	        return person.getFirstName()+" "+person.getLastName();
	    }
	    
	
	
}

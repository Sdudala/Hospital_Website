package com.neu.healthcare.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class MedicationOrder implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="MEDICATIONORDER_ID", unique=true, nullable=false)
	private int medicationOrderId;
	
	@Column(name = "DRUG", nullable=false, length=200)
	private String drug;
	
	@Column(name = "DOSAGE", nullable=false, length=200)
	private int dosage = 0;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Appointment appointment;
	

	public MedicationOrder() {
	
	}

	public String getDrug() {
		return drug;
	}

	public void setDrug(String drug) {
		this.drug = drug;
	}

	public int getDosage() {
		return dosage;
	}

	public void setDosage(int dosage) {
		this.dosage = dosage;
	}

	public int getMedicationOrderId() {
		return medicationOrderId;
	}

	public void setMedicationOrderId(int medicationOrderId) {
		this.medicationOrderId = medicationOrderId;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	
	

}

package com.neu.healthcare.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

@Entity
@FilterDef(name="appointmentStatusFilter", parameters=@ParamDef(name="status", type="string"))
@Filters( {
    @Filter(name="appointmentStatusFilter", condition=":status = status")
} )
public class Appointment implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="APPOINTMENT_ID", unique=true, nullable=false)
	private int appointmentId;
	
	@Column(name = "DATE", nullable=false, length=200)
	@NotNull
	private Date date;
	
	@Column(name = "COMPLAINT", nullable=false, length=200)
	@NotNull
	private String complaint;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private UserAccount doctor;
	
	private int doctorId;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	@NotNull
	private Patient patient;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private VitalSigns vitalSigns;
	
	@Column(name = "DIAGNOSIS", nullable=true, length=200)
	private String diagnosis = "N/A";
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private Status status = Status.Pending;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PAYMENTSTATUS")
	private PaymentStatus paymentStatus = PaymentStatus.Due;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="appointment", cascade=CascadeType.ALL)
	private List<MedicationOrder> medicationList;
	
	@Column(name = "TOTALBILL", nullable=false, length=200)
	private int totalBill;
	
	public enum Status{
		Pending("Pending"),
        InProcess("InProcess"),
        Done("Done");
            
        private String value;
        private Status(String value){
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
	
	public enum PaymentStatus{
		Due("Due"),
		BillGenerated("BillGenerated"),
        NoDue("NoDue");
                   
        private String value;
        private PaymentStatus(String value){
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
		
	public Appointment() {
		
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getComplaint() {
		return complaint;
	}

	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}

	public UserAccount getDoctor() {
		return doctor;
	}

	public VitalSigns getVitalSigns() {
		return vitalSigns;
	}

	public void setVitalSigns(VitalSigns vitalSigns) {
		this.vitalSigns = vitalSigns;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setDoctor(UserAccount doctor) {
		this.doctor = doctor;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public List<MedicationOrder> getMedicationList() {
		return medicationList;
	}

	public void setMedicationList(List<MedicationOrder> medicationList) {
		this.medicationList = medicationList;
	}
	
	public void addMedication(MedicationOrder medication) {
		medicationList.add(medication);
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	public int getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(int totalBill) {
		this.totalBill = totalBill;
	}

	public void calculateBill() {
		totalBill = 50;
		for(MedicationOrder mo : medicationList) {
			totalBill += mo.getDosage();
		}
	}
		
}

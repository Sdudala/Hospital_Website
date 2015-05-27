package com.neu.healthcare.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VitalSigns implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="VITALSIGNS_ID", unique=true, nullable=false)
	private int vitalSignsId;
	
	@Column(name = "TEMP")
	private Float temperature;
	
	@Column(name = "RESP_RATE")
    private Float respRate;
    
	@Column(name = "HEART_RATE")
    private Float heartRate;
    
	@Column(name = "BP")
    private Float bloodPressure;
    
	@Column(name = "WEIGHT")
    private Float weightPounds;
    
    public VitalSigns() {
	}

	public int getVitalSignsId() {
		return vitalSignsId;
	}

	public void setVitalSignsId(int vitalSignsId) {
		this.vitalSignsId = vitalSignsId;
	}

	public Float getTemperature() {
		return temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}

	public Float getRespRate() {
		return respRate;
	}

	public void setRespRate(Float respRate) {
		this.respRate = respRate;
	}

	public Float getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(Float heartRate) {
		this.heartRate = heartRate;
	}

	public Float getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(Float bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public Float getWeightPounds() {
		return weightPounds;
	}

	public void setWeightPounds(Float weightPounds) {
		this.weightPounds = weightPounds;
	}
    
    
    
    

}

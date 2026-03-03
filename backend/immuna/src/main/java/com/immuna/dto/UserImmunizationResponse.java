package com.immuna.dto;

import java.time.LocalDate;

public class UserImmunizationResponse {

	private Long recordId;
	private String vaccineName;
	private Integer doseNo;
	private String doseType;
	private LocalDate dateTaken;
	private LocalDate nextDueDate;
	private String status;

	public UserImmunizationResponse(Long recordId, String vaccineName, 
			Integer doseNo, String doseType,
			LocalDate dateTaken, LocalDate nextDueDate, String status) {
		this.recordId = recordId;
		this.vaccineName = vaccineName;
		this.doseNo = doseNo;
		this.doseType = doseType;
		this.dateTaken = dateTaken;
		this.nextDueDate = nextDueDate;
		this.status = status;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public Integer getDoseNo() {
		return doseNo;
	}

	public void setDoseNo(Integer doseNo) {
		this.doseNo = doseNo;
	}

	public String getDoseType() {
		return doseType;
	}

	public void setDoseType(String doseType) {
		this.doseType = doseType;
	}

	public LocalDate getDateTaken() {
		return dateTaken;
	}

	public void setDateTaken(LocalDate dateTaken) {
		this.dateTaken = dateTaken;
	}

	public LocalDate getNextDueDate() {
		return nextDueDate;
	}

	public void setNextDueDate(LocalDate nextDueDate) {
		this.nextDueDate = nextDueDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}

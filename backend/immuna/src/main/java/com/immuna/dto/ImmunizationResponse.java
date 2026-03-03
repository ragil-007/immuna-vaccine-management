package com.immuna.dto;

import java.time.LocalDate;

public class ImmunizationResponse {

	private Long recordId;
	private Long userId;        // ADD
    private Long scheduleId;    // ADD
	private String userName;
	private String vaccineName;
	private Integer doseNo;
	private LocalDate dateTaken;
	private LocalDate nextDueDate;
	
	public ImmunizationResponse() {}

	public ImmunizationResponse(Long recordId, String userName, 
			String vaccineName, Integer doseNo, 
			LocalDate dateTaken,LocalDate nextDueDate) {
		this.recordId = recordId;
		this.userName = userName;
		this.vaccineName = vaccineName;
		this.doseNo = doseNo;
		this.dateTaken = dateTaken;
		this.nextDueDate = nextDueDate;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
	
	

}

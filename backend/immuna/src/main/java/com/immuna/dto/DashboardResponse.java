package com.immuna.dto;

import java.time.LocalDate;

public class DashboardResponse {

	private String vaccineName;
	private Integer doseNo;
	private String doseType;
	private LocalDate nextDoseDate;
	private long daysRemaining;
	private String status;

	private String recentVaccineName;
	private Integer recentDoseNo;
	private LocalDate recentDateTaken;

	public DashboardResponse(String vaccineName, Integer doseNo, String doseType, 
			LocalDate nextDoseDate, long daysRemaining, String status, 
			String recentVaccineName, Integer recentDoseNo, 
			LocalDate recentDateTaken) {

		this.vaccineName = vaccineName;
		this.doseNo = doseNo;
		this.doseType = doseType;
		this.nextDoseDate = nextDoseDate;
		this.daysRemaining = daysRemaining;
		this.status = status;
		this.recentVaccineName = recentVaccineName;
		this.recentDoseNo = recentDoseNo;
		this.recentDateTaken = recentDateTaken;
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

	public LocalDate getNextDoseDate() {
		return nextDoseDate;
	}

	public void setNextDoseDate(LocalDate nextDoseDate) {
		this.nextDoseDate = nextDoseDate;
	}

	public long getDaysRemaining() {
		return daysRemaining;
	}

	public void setDaysRemaining(long daysRemaining) {
		this.daysRemaining = daysRemaining;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRecentVaccineName() {
		return recentVaccineName;
	}

	public void setRecentVaccineName(String recentVaccineName) {
		this.recentVaccineName = recentVaccineName;
	}

	public Integer getRecentDoseNo() {
		return recentDoseNo;
	}

	public void setRecentDoseNo(Integer recentDoseNo) {
		this.recentDoseNo = recentDoseNo;
	}

	public LocalDate getRecentDateTaken() {
		return recentDateTaken;
	}

	public void setRecentDateTaken(LocalDate recentDateTaken) {
		this.recentDateTaken = recentDateTaken;
	}
	
	

}

package com.immuna.dto;

public class ScheduleResponse {

	private Long scheduleId;
	private Long vaccineId; 
	private String vaccineName;
	private Integer doseNo;
	private Integer gapDays;
	private String doseType;
	private Boolean isRecurring;
	private Integer maxOccurrences;
	
	public ScheduleResponse() {}

	public ScheduleResponse(Long scheduleId, Long vaccineId, String vaccineName, 
			Integer doseNo, Integer gapDays, String doseType,
			Boolean isRecurring, Integer maxOccurrences) {
		this.scheduleId = scheduleId;
		this.vaccineName = vaccineName;
		this.doseNo = doseNo;
		this.gapDays = gapDays;
		this.doseType = doseType;
		this.isRecurring = isRecurring;
		this.maxOccurrences = maxOccurrences;
	}

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Long getVaccineId() {
		return vaccineId;
	}

	public void setVaccineId(Long vaccineId) {
		this.vaccineId = vaccineId;
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

	public Integer getGapDays() {
		return gapDays;
	}

	public void setGapDays(Integer gapDays) {
		this.gapDays = gapDays;
	}

	public String getDoseType() {
		return doseType;
	}

	public void setDoseType(String doseType) {
		this.doseType = doseType;
	}

	public Boolean getIsRecurring() {
		return isRecurring;
	}

	public void setIsRecurring(Boolean isRecurring) {
		this.isRecurring = isRecurring;
	}

	public Integer getMaxOccurrences() {
		return maxOccurrences;
	}

	public void setMaxOccurrences(Integer maxOccurrences) {
		this.maxOccurrences = maxOccurrences;
	}
	
	

}

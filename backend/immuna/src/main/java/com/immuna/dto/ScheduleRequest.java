package com.immuna.dto;

import jakarta.validation.constraints.NotNull;

public class ScheduleRequest {

	@NotNull
	private Long vaccineId;

	@NotNull
	private Integer doseNo;

	@NotNull
	private Integer gapDays;

	@NotNull
	private String doseType; // PRIMARY / BOOSTER

	@NotNull
	private Boolean isRecurring;

	@NotNull
	private Integer maxOccurrences;

	public Long getVaccineId() {
		return vaccineId;
	}

	public void setVaccineId(Long vaccineId) {
		this.vaccineId = vaccineId;
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

	public void setIsRecurring(Boolean recurring) {
		isRecurring = recurring;
	}

	public Integer getMaxOccurrences() {
		return maxOccurrences;
	}

	public void setMaxOccurrences(Integer maxOccurrences) {
		this.maxOccurrences = maxOccurrences;
	}

}

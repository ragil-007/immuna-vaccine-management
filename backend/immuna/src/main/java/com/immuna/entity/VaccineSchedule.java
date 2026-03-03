package com.immuna.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "vaccine_schedules",
	uniqueConstraints = @UniqueConstraint(columnNames = {"vaccine_id", "dose_no"}))
public class VaccineSchedule {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long scheduleId;

    @ManyToOne
    @JoinColumn(name = "vaccine_id", nullable = false)
    private Vaccine vaccine;

    @Column(name = "dose_no", nullable = false)
    private Integer doseNo;

    @Column(name = "gap_days", nullable = false)
    private Integer gapDays;

    @Enumerated(EnumType.STRING)
    @Column(name = "dose_type", nullable = false)
    private DoseType doseType;

    @Column(name = "is_recurring", nullable = false)
    private Boolean isRecurring;

    @Column(name = "max_occurrences", nullable = false)
    private Integer maxOccurrences;

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Vaccine getVaccine() {
		return vaccine;
	}

	public void setVaccine(Vaccine vaccine) {
		this.vaccine = vaccine;
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

	public DoseType getDoseType() {
		return doseType;
	}

	public void setDoseType(DoseType doseType) {
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

package com.immuna.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class VaccineRequest {

	@NotBlank(message = "Vaccine name is required")
	private String vaccineName;

	private String description;

	private String manufacturer;

	@NotEmpty(message = "At least one disease must be selected")
	private List<Long> diseaseIds;

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public List<Long> getDiseaseIds() {
		return diseaseIds;
	}

	public void setDiseaseIds(List<Long> diseaseIds) {
		this.diseaseIds = diseaseIds;
	}

}

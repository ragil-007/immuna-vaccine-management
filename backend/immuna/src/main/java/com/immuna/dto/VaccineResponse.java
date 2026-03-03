package com.immuna.dto;

import java.util.List;

public class VaccineResponse {

	private Long vaccineId;
	private String vaccineName;
	private String description;
	private String manufacturer;
	private List<String> diseases;

	public VaccineResponse(Long vaccineId, String vaccineName, String description, String manufacturer,
			List<String> diseases) {
		this.vaccineId = vaccineId;
		this.vaccineName = vaccineName;
		this.description = description;
		this.manufacturer = manufacturer;
		this.diseases = diseases;
	}

	public Long getVaccineId() {
		return vaccineId;
	}

	public String getVaccineName() {
		return vaccineName;
	}

	public String getDescription() {
		return description;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public List<String> getDiseases() {
		return diseases;
	}

}

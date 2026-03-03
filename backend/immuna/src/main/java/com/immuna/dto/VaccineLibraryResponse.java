package com.immuna.dto;

import java.util.List;

public class VaccineLibraryResponse {

	private Long vaccineId;
	private String vaccineName;
	private String description;
	private String manufacturer;
	private List<DiseaseInfo> diseases;

	public VaccineLibraryResponse(Long vaccineId, String vaccineName, 
			String description, String manufacturer,
			List<DiseaseInfo> diseases) {
		this.vaccineId = vaccineId;
		this.vaccineName = vaccineName;
		this.description = description;
		this.manufacturer = manufacturer;
		this.diseases = diseases;
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

	public List<DiseaseInfo> getDiseases() {
		return diseases;
	}

	public void setDiseases(List<DiseaseInfo> diseases) {
		this.diseases = diseases;
	}

}

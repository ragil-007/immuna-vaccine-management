package com.immuna.dto;

public class DiseaseInfo {

	private String diseaseName;
	private String description;

	public DiseaseInfo(String diseaseName, String description) {
		this.diseaseName = diseaseName;
		this.description = description;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

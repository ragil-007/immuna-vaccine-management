package com.immuna.dto;

import jakarta.validation.constraints.NotBlank;

public class DiseaseRequest {
	
	@NotBlank(message = "Disease name is required")
    private String diseaseName;

    private String description;

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

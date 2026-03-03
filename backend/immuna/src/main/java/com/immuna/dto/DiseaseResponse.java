package com.immuna.dto;

public class DiseaseResponse {
	
	private Long diseaseId;
    private String diseaseName;
    private String description;

    public DiseaseResponse(Long diseaseId, String diseaseName, String description) {
        this.diseaseId = diseaseId;
        this.diseaseName = diseaseName;
        this.description = description;
    }

    public Long getDiseaseId() {
        return diseaseId;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public String getDescription() {
        return description;
    }

}

package com.immuna.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="disease", 
	uniqueConstraints = @UniqueConstraint(columnNames = "disease_name"))
public class Disease {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disease_id")
	private Long diseaseId;
	
	@Column(name = "disease_name")
	private String diseaseName;
	
	@Column(name = "description")
	private String description;
	
	public Disease() {}

	public Long getDiseaseId() {
		return diseaseId;
	}

	public void setFloatId(Long diseaseId) {
		this.diseaseId = diseaseId;
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

package com.immuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.immuna.entity.Disease;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {
	
	boolean existsByDiseaseName(String diseaseName);

}

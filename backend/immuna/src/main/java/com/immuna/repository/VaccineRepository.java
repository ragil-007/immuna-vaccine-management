package com.immuna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.immuna.entity.Vaccine;

public interface VaccineRepository extends JpaRepository<Vaccine, Long>{
	
	boolean existsByVaccineName(String vaccineName);
	
	List<Vaccine> findByVaccineNameContainingIgnoreCase(String vaccineName);

}

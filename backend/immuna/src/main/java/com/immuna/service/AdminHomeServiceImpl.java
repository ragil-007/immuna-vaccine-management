package com.immuna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immuna.dto.AdminHomeSummary;
import com.immuna.repository.DiseaseRepository;
import com.immuna.repository.ImmunizationRecordRepository;
import com.immuna.repository.UserRepository;
import com.immuna.repository.VaccineRepository;

@Service
public class AdminHomeServiceImpl implements AdminHomeService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VaccineRepository vaccineRepository;
	
	@Autowired
	private DiseaseRepository diseaseRepository;
	
	@Autowired
	private ImmunizationRecordRepository immunizationRecordRepository;

	@Override
	public AdminHomeSummary getSummary() {
		long totalUsers = userRepository.count();
	    long totalVaccines = vaccineRepository.count();
	    long totalDiseases = diseaseRepository.count();
	    long totalRecords = immunizationRecordRepository.count();

	    return new AdminHomeSummary(totalUsers, totalVaccines, totalDiseases, totalRecords);
	}

}

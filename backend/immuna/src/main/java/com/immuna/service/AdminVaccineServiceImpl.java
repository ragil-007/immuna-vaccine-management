package com.immuna.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immuna.dto.VaccineRequest;
import com.immuna.dto.VaccineResponse;
import com.immuna.entity.Disease;
import com.immuna.entity.Vaccine;
import com.immuna.exception.BadRequestException;
import com.immuna.exception.ResourceNotFoundException;
import com.immuna.repository.DiseaseRepository;
import com.immuna.repository.VaccineRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminVaccineServiceImpl implements AdminVaccineService{
	
	@Autowired
	private VaccineRepository vaccineRepository;
	
	@Autowired
	private DiseaseRepository diseaseRepository;

	@Override
	@Transactional
	public VaccineResponse createVaccine(VaccineRequest request) {
		if (vaccineRepository.existsByVaccineName(request.getVaccineName())) {
            throw new BadRequestException("Vaccine already exists");
        }

        Vaccine vaccine = new Vaccine();
        vaccine.setVaccineName(request.getVaccineName());
        vaccine.setDescription(request.getDescription());
        vaccine.setManufacturer(request.getManufacturer());

        Set<Disease> diseases =
                new HashSet<>(diseaseRepository.findAllById(request.getDiseaseIds()));

        vaccine.setDiseases(diseases);

        vaccineRepository.save(vaccine);

        return mapToResponse(vaccine);
	}

	@Override
	public List<VaccineResponse> getAllVaccines() {
		return vaccineRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
	}

	@Override
	public VaccineResponse getVaccineById(Long id) {
		Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine not found"));

        return mapToResponse(vaccine);
	}

	@Override
	@Transactional
	public VaccineResponse updateVaccine(Long id, VaccineRequest request) {
		Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine not found"));

        vaccine.setVaccineName(request.getVaccineName());
        vaccine.setDescription(request.getDescription());
        vaccine.setManufacturer(request.getManufacturer());

        Set<Disease> diseases =
                new HashSet<>(diseaseRepository.findAllById(request.getDiseaseIds()));

        vaccine.setDiseases(diseases);

        vaccineRepository.save(vaccine);

        return mapToResponse(vaccine);
	}

	@Override
	@Transactional
	public void deleteVaccine(Long id) {
		Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine not found"));

        vaccineRepository.delete(vaccine);
		
	}
	
	private VaccineResponse mapToResponse(Vaccine vaccine) {

        List<String> diseaseNames = vaccine.getDiseases()
                .stream()
                .map(Disease::getDiseaseName)
                .collect(Collectors.toList());

        return new VaccineResponse(
                vaccine.getVaccineId(),
                vaccine.getVaccineName(),
                vaccine.getDescription(),
                vaccine.getManufacturer(),
                diseaseNames
        );
    }

}

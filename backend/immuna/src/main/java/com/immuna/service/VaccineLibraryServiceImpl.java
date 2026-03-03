package com.immuna.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immuna.dto.DiseaseInfo;
import com.immuna.dto.VaccineLibraryResponse;
import com.immuna.entity.Vaccine;
import com.immuna.exception.ResourceNotFoundException;
import com.immuna.repository.VaccineRepository;

@Service
public class VaccineLibraryServiceImpl implements VaccineLibraryService{
	
	@Autowired
	private VaccineRepository vaccineRepository;

	@Override
	public List<VaccineLibraryResponse> getAllVaccines() {
		return vaccineRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
	}

	@Override
	public List<VaccineLibraryResponse> searchVaccines(String keyword) {
		return vaccineRepository
                .findByVaccineNameContainingIgnoreCase(keyword)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
	}

	@Override
	public VaccineLibraryResponse getVaccineById(Long id) {
		Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine not found"));

        return mapToResponse(vaccine);
	}
	
	private VaccineLibraryResponse mapToResponse(Vaccine vaccine) {

        List<DiseaseInfo> diseases = vaccine.getDiseases()
                .stream()
                .map(d -> new DiseaseInfo(
                        d.getDiseaseName(),
                        d.getDescription()
                ))
                .collect(Collectors.toList());

        return new VaccineLibraryResponse(
                vaccine.getVaccineId(),
                vaccine.getVaccineName(),
                vaccine.getDescription(),
                vaccine.getManufacturer(),
                diseases
        );
    }
	

}

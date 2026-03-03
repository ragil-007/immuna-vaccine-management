package com.immuna.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immuna.dto.DiseaseRequest;
import com.immuna.dto.DiseaseResponse;
import com.immuna.entity.Disease;
import com.immuna.exception.BadRequestException;
import com.immuna.exception.ResourceNotFoundException;
import com.immuna.repository.DiseaseRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminDiseaseServiceImpl implements AdminDiseaseService{
	
	@Autowired
	private DiseaseRepository diseaseRepository;

	@Override
	@Transactional
	public DiseaseResponse createDisease(DiseaseRequest request) {
		if (diseaseRepository.existsByDiseaseName(request.getDiseaseName())) {
			throw new BadRequestException("Disease already exists");
        }

        Disease disease = new Disease();
        disease.setDiseaseName(request.getDiseaseName());
        disease.setDescription(request.getDescription());

        diseaseRepository.save(disease);

        return new DiseaseResponse(
                disease.getDiseaseId(),
                disease.getDiseaseName(),
                disease.getDescription()
        );
	}

	@Override
	public List<DiseaseResponse> getAllDiseases() {
		return diseaseRepository.findAll()
                .stream()
                .map(d -> new DiseaseResponse(
                        d.getDiseaseId(),
                        d.getDiseaseName(),
                        d.getDescription()
                ))
                .collect(Collectors.toList());
	}

	@Override
	@Transactional
	public DiseaseResponse updateDisease(Long id, DiseaseRequest request) {
		Disease disease = diseaseRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Disease not found"));

	    // Update existing object
	    disease.setDiseaseName(request.getDiseaseName());
	    disease.setDescription(request.getDescription());

	    diseaseRepository.save(disease);

	    return new DiseaseResponse(
	            disease.getDiseaseId(),
	            disease.getDiseaseName(),
	            disease.getDescription()
	    );
	}

	@Override
	@Transactional
	public void deleteDisease(Long id) {
		Disease disease = diseaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disease not found"));

        diseaseRepository.delete(disease);
		
	}

}

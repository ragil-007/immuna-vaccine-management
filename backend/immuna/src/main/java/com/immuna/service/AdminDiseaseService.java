package com.immuna.service;

import java.util.List;

import com.immuna.dto.DiseaseRequest;
import com.immuna.dto.DiseaseResponse;

public interface AdminDiseaseService {
	
	DiseaseResponse createDisease(DiseaseRequest request);

    List<DiseaseResponse> getAllDiseases();

    DiseaseResponse updateDisease(Long id, DiseaseRequest request);

    void deleteDisease(Long id);

}

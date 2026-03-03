package com.immuna.service;

import java.util.List;

import com.immuna.dto.VaccineRequest;
import com.immuna.dto.VaccineResponse;

public interface AdminVaccineService {
	
	VaccineResponse createVaccine(VaccineRequest request);

    List<VaccineResponse> getAllVaccines();

    VaccineResponse getVaccineById(Long id);

    VaccineResponse updateVaccine(Long id, VaccineRequest request);

    void deleteVaccine(Long id);

}

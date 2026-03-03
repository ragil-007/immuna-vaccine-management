package com.immuna.service;

import java.util.List;

import com.immuna.dto.VaccineLibraryResponse;

public interface VaccineLibraryService {
	
	List<VaccineLibraryResponse> getAllVaccines();

    List<VaccineLibraryResponse> searchVaccines(String keyword);

    VaccineLibraryResponse getVaccineById(Long id);

}

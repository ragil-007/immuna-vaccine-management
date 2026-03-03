package com.immuna.service;

import java.util.List;

import com.immuna.dto.ImmunizationRequest;
import com.immuna.dto.ImmunizationResponse;

public interface AdminImmunizationService {

	ImmunizationResponse addRecord(ImmunizationRequest request);

	List<ImmunizationResponse> getAllRecords();
	
	ImmunizationResponse getById(Long recordId);

	ImmunizationResponse updateRecord(Long recordId, ImmunizationRequest request);

	List<ImmunizationResponse> getRecordsByUser(Long userId);

	void deleteRecord(Long recordId);

}

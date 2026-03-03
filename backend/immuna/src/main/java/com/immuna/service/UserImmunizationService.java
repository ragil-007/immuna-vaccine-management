package com.immuna.service;

import java.util.List;

import com.immuna.dto.UserImmunizationResponse;

public interface UserImmunizationService {
	
	List<UserImmunizationResponse> getVaccinationHistory(Long userId);

    List<UserImmunizationResponse> getUpcomingDoses(Long userId);

    List<UserImmunizationResponse> getOverdueDoses(Long userId);

}

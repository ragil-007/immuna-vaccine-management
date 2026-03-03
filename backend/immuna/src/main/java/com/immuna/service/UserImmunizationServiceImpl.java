package com.immuna.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immuna.dto.UserImmunizationResponse;
import com.immuna.entity.ImmunizationRecord;
import com.immuna.entity.User;
import com.immuna.exception.ResourceNotFoundException;
import com.immuna.repository.ImmunizationRecordRepository;
import com.immuna.repository.UserRepository;

@Service
public class UserImmunizationServiceImpl implements UserImmunizationService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private ImmunizationRecordRepository recordRepository;

	@Override
	public List<UserImmunizationResponse> getVaccinationHistory(Long userId) {
		User user = getUser(userId);

        return recordRepository.findByUserOrderByDateTakenDesc(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
	}

	@Override
	public List<UserImmunizationResponse> getUpcomingDoses(Long userId) {
		User user = getUser(userId);
        LocalDate today = LocalDate.now();

        return recordRepository.findByUser(user)
                .stream()
                .filter(r -> r.getNextDueDate() != null && r.getNextDueDate().isAfter(today))
                .map(r -> mapToResponseWithStatus(r, "UPCOMING"))
                .collect(Collectors.toList());
	}

	@Override
	public List<UserImmunizationResponse> getOverdueDoses(Long userId) {
		User user = getUser(userId);
        LocalDate today = LocalDate.now();

        return recordRepository.findByUser(user)
                .stream()
                .filter(r -> r.getNextDueDate() != null && r.getNextDueDate().isBefore(today))
                .map(r -> mapToResponseWithStatus(r, "OVERDUE"))
                .collect(Collectors.toList());
	}
	
	private User getUser(Long userId) {

        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private UserImmunizationResponse mapToResponse(ImmunizationRecord record) {

        String status = "COMPLETED";

        if (record.getNextDueDate() != null) {
            if (record.getNextDueDate().isBefore(LocalDate.now())) {
                status = "OVERDUE";
            } else {
                status = "UPCOMING";
            }
        }

        return new UserImmunizationResponse(
                record.getRecordId(),
                record.getSchedule().getVaccine().getVaccineName(),
                record.getSchedule().getDoseNo(),
                record.getSchedule().getDoseType().name(),
                record.getDateTaken(),
                record.getNextDueDate(),
                status
        );
    }

    private UserImmunizationResponse mapToResponseWithStatus(ImmunizationRecord record,
                                                             String status) {

        return new UserImmunizationResponse(
                record.getRecordId(),
                record.getSchedule().getVaccine().getVaccineName(),
                record.getSchedule().getDoseNo(),
                record.getSchedule().getDoseType().name(),
                record.getDateTaken(),
                record.getNextDueDate(),
                status
        );
    }

}

package com.immuna.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immuna.dto.ImmunizationRequest;
import com.immuna.dto.ImmunizationResponse;
import com.immuna.entity.ImmunizationRecord;
import com.immuna.entity.Role;
import com.immuna.entity.User;
import com.immuna.entity.VaccineSchedule;
import com.immuna.exception.InvalidUserRoleException;
import com.immuna.exception.ResourceNotFoundException;
import com.immuna.repository.ImmunizationRecordRepository;
import com.immuna.repository.UserRepository;
import com.immuna.repository.VaccineScheduleRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminImmunizationServiceImpl implements AdminImmunizationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VaccineScheduleRepository scheduleRepository;

	@Autowired
	private ImmunizationRecordRepository recordRepository;

	@Override
	@Transactional
	public ImmunizationResponse addRecord(ImmunizationRequest request) {
		User user = userRepository.findById(request.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		if (user.getRole() != Role.USER) {
			throw new InvalidUserRoleException("Immunization records can only be added for normal users.");
		}

		VaccineSchedule schedule = scheduleRepository.findById(request.getScheduleId())
				.orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));

		LocalDate nextDue = calculateNextDue(user, schedule, request.getDateTaken());

		ImmunizationRecord record = new ImmunizationRecord();
		record.setUser(user);
		record.setSchedule(schedule);
		record.setDateTaken(request.getDateTaken());
		record.setNextDueDate(nextDue);

		recordRepository.save(record);

		return mapToResponse(record);
	}

	@Override
	public List<ImmunizationResponse> getAllRecords() {
		return recordRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	@Override
	public List<ImmunizationResponse> getRecordsByUser(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		return recordRepository.findByUser(user).stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void deleteRecord(Long recordId) {
		ImmunizationRecord record = recordRepository.findById(recordId)
				.orElseThrow(() -> new ResourceNotFoundException("Record not found"));

		recordRepository.delete(record);

	}

	private LocalDate calculateNextDue(User user, VaccineSchedule schedule, LocalDate dateTaken) {

		// Find next dose
		Optional<VaccineSchedule> nextSchedule = scheduleRepository.findByVaccineAndDoseNo(schedule.getVaccine(),
				schedule.getDoseNo() + 1);

		// No next dose exists
		if (nextSchedule.isEmpty()) {
			return null;
		}

		VaccineSchedule next = nextSchedule.get();

		// Calculate using NEXT dose gap
		return dateTaken.plusDays(next.getGapDays());
	}

	private ImmunizationResponse mapToResponse(ImmunizationRecord record) {

		ImmunizationResponse response = new ImmunizationResponse();

		response.setRecordId(record.getRecordId());

		// 🔥 THESE ARE MISSING IN YOUR CURRENT VERSION
		response.setUserId(record.getUser().getUserId());
		response.setScheduleId(record.getSchedule().getScheduleId());

		response.setUserName(record.getUser().getFullName());
		response.setVaccineName(record.getSchedule().getVaccine().getVaccineName());
		response.setDoseNo(record.getSchedule().getDoseNo());

		response.setDateTaken(record.getDateTaken());
		response.setNextDueDate(record.getNextDueDate());

		return response;
	}

	@Override
	public ImmunizationResponse getById(Long recordId) {
		ImmunizationRecord record = recordRepository.findById(recordId)
				.orElseThrow(() -> new RuntimeException("Record not found"));

		return mapToResponse(record);
	}

	@Override
	@Transactional
	public ImmunizationResponse updateRecord(Long recordId, ImmunizationRequest request) {

		ImmunizationRecord record = recordRepository.findById(recordId)
				.orElseThrow(() -> new ResourceNotFoundException("Record not found"));

		User user = userRepository.findById(request.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		if (user.getRole() != Role.USER) {
			throw new InvalidUserRoleException("Immunization records can only be updated for normal users.");
		}

		VaccineSchedule schedule = scheduleRepository.findById(request.getScheduleId())
				.orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));

		// Recalculate next due date using your existing logic
		LocalDate nextDue = calculateNextDue(user, schedule, request.getDateTaken());

		// Update fields
		record.setUser(user);
		record.setSchedule(schedule);
		record.setDateTaken(request.getDateTaken());
		record.setNextDueDate(nextDue);

		recordRepository.save(record);

		return mapToResponse(record);
	}

}

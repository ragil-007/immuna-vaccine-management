package com.immuna.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immuna.dto.ScheduleRequest;
import com.immuna.dto.ScheduleResponse;
import com.immuna.entity.DoseType;
import com.immuna.entity.Vaccine;
import com.immuna.entity.VaccineSchedule;
import com.immuna.exception.BadRequestException;
import com.immuna.exception.ResourceNotFoundException;
import com.immuna.repository.VaccineRepository;
import com.immuna.repository.VaccineScheduleRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminScheduleServiceImpl implements AdminScheduleService {

	@Autowired
	private VaccineRepository vaccineRepository;

	@Autowired
	private VaccineScheduleRepository scheduleRepository;

	@Override
	@Transactional
	public ScheduleResponse createSchedule(ScheduleRequest request) {

		Vaccine vaccine = vaccineRepository.findById(request.getVaccineId())
				.orElseThrow(() -> new ResourceNotFoundException("Vaccine not found"));

		if (scheduleRepository.existsByVaccineAndDoseNo(vaccine, request.getDoseNo())) {
			throw new BadRequestException("Dose number already exists for this vaccine");
		}

		VaccineSchedule schedule = new VaccineSchedule();
		schedule.setVaccine(vaccine);
		schedule.setDoseNo(request.getDoseNo());
		schedule.setGapDays(request.getGapDays());
		schedule.setDoseType(DoseType.valueOf(request.getDoseType()));
		schedule.setIsRecurring(request.getIsRecurring());

		// BUSINESS RULE ENFORCEMENT
		if (!Boolean.TRUE.equals(request.getIsRecurring())) {
			schedule.setMaxOccurrences(1);
		} else {
			schedule.setMaxOccurrences(request.getMaxOccurrences());
		}

		scheduleRepository.save(schedule);

		return mapToResponse(schedule);
	}

	@Override
	public List<ScheduleResponse> getSchedulesByVaccine(Long vaccineId) {
		return scheduleRepository.findByVaccine_VaccineId(vaccineId)
				.stream()
				.map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public ScheduleResponse updateSchedule(Long id, ScheduleRequest request) {

		VaccineSchedule schedule = scheduleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));

		schedule.setDoseNo(request.getDoseNo());
		schedule.setGapDays(request.getGapDays());
		schedule.setDoseType(DoseType.valueOf(request.getDoseType()));
		schedule.setIsRecurring(request.getIsRecurring());

		// BUSINESS RULE ENFORCEMENT
		if (!Boolean.TRUE.equals(request.getIsRecurring())) {
			schedule.setMaxOccurrences(1);
		} else {
			schedule.setMaxOccurrences(request.getMaxOccurrences());
		}

		scheduleRepository.save(schedule);

		return mapToResponse(schedule);
	}

	@Override
	@Transactional
	public void deleteSchedule(Long id) {
		VaccineSchedule schedule = scheduleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));

		scheduleRepository.delete(schedule);
	}

	@Override
	public List<ScheduleResponse> getAllSchedules() {
		return scheduleRepository.findAll()
				.stream()
				.map(this::mapToResponse)
				.toList();
	}

	private ScheduleResponse mapToResponse(VaccineSchedule schedule) {

		ScheduleResponse response = new ScheduleResponse();

		response.setScheduleId(schedule.getScheduleId());
		response.setVaccineId(schedule.getVaccine().getVaccineId());
		response.setVaccineName(schedule.getVaccine().getVaccineName());
		response.setDoseNo(schedule.getDoseNo());
		response.setGapDays(schedule.getGapDays());
		response.setDoseType(schedule.getDoseType().name());
		response.setIsRecurring(schedule.getIsRecurring());
		response.setMaxOccurrences(schedule.getMaxOccurrences());

		return response;
	}
}
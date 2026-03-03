package com.immuna.service;

import java.util.List;

import com.immuna.dto.ScheduleRequest;
import com.immuna.dto.ScheduleResponse;

public interface AdminScheduleService {
	
	ScheduleResponse createSchedule(ScheduleRequest request);

    List<ScheduleResponse> getSchedulesByVaccine(Long vaccineId);
    
    List<ScheduleResponse> getAllSchedules();

    ScheduleResponse updateSchedule(Long id, ScheduleRequest request);

    void deleteSchedule(Long id);

}

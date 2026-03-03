package com.immuna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.immuna.dto.ScheduleRequest;
import com.immuna.dto.ScheduleResponse;
import com.immuna.service.AdminScheduleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/schedules")
public class AdminScheduleController {
	
	@Autowired
	private AdminScheduleService adminScheduleService;

    @PostMapping
    public ScheduleResponse create(@Valid @RequestBody ScheduleRequest request) {
        return adminScheduleService.createSchedule(request);
    }

    @GetMapping("/vaccine/{vaccineId}")
    public List<ScheduleResponse> getByVaccine(@PathVariable Long vaccineId) {
        return adminScheduleService.getSchedulesByVaccine(vaccineId);
    }
    
    @GetMapping
    public List<ScheduleResponse> getAll() {
        return adminScheduleService.getAllSchedules();
    }

    @PutMapping("/{id}")
    public ScheduleResponse update(@PathVariable Long id,
                                   @Valid @RequestBody ScheduleRequest request) {
        return adminScheduleService.updateSchedule(id, request);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        adminScheduleService.deleteSchedule(id);
        return "Schedule deleted successfully";
    }
	
	

}

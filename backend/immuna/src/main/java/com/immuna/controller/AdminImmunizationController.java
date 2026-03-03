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

import com.immuna.dto.ImmunizationRequest;
import com.immuna.dto.ImmunizationResponse;
import com.immuna.service.AdminImmunizationService;

@RestController
@RequestMapping("/api/admin/immunizations")
public class AdminImmunizationController {
	
	@Autowired
	private final AdminImmunizationService adminImmunizationService;

    public AdminImmunizationController(AdminImmunizationService adminImmunizationService) {
        this.adminImmunizationService = adminImmunizationService;
    }

    @PostMapping
    public ImmunizationResponse addRecord(@RequestBody ImmunizationRequest request) {
        return adminImmunizationService.addRecord(request);
    }

    @GetMapping
    public List<ImmunizationResponse> getAll() {
        return adminImmunizationService.getAllRecords();
    }

    @GetMapping("/user/{userId}")
    public List<ImmunizationResponse> getByUser(@PathVariable Long userId) {
        return adminImmunizationService.getRecordsByUser(userId);
    }
    
    @GetMapping("/{recordId}")
    public ImmunizationResponse getById(@PathVariable Long recordId) {
        return adminImmunizationService.getById(recordId);
    }

    @PutMapping("/{recordId}")
    public ImmunizationResponse updateRecord(
            @PathVariable Long recordId,
            @RequestBody ImmunizationRequest request) {

        return adminImmunizationService.updateRecord(recordId, request);
    }

    @DeleteMapping("/{recordId}")
    public String delete(@PathVariable Long recordId) {
        adminImmunizationService.deleteRecord(recordId);
        return "Record deleted successfully";
    }

}

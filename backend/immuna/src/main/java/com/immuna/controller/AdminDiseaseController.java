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

import com.immuna.dto.DiseaseRequest;
import com.immuna.dto.DiseaseResponse;
import com.immuna.service.AdminDiseaseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/diseases")
public class AdminDiseaseController {
	
	@Autowired
	private final AdminDiseaseService adminDiseaseService;

    public AdminDiseaseController(AdminDiseaseService adminDiseaseService) {
        this.adminDiseaseService = adminDiseaseService;
    }

    @PostMapping
    public DiseaseResponse createDisease(@Valid @RequestBody DiseaseRequest request) {
        return adminDiseaseService.createDisease(request);
    }

    @GetMapping
    public List<DiseaseResponse> getAllDiseases() {
        return adminDiseaseService.getAllDiseases();
    }

    @PutMapping("/{id}")
    public DiseaseResponse updateDisease(@PathVariable Long id,
                                         @Valid @RequestBody DiseaseRequest request) {
        return adminDiseaseService.updateDisease(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteDisease(@PathVariable Long id) {
        adminDiseaseService.deleteDisease(id);
        return "Disease deleted successfully";
    }

}

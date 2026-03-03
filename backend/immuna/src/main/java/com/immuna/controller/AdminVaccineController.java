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

import com.immuna.dto.VaccineRequest;
import com.immuna.dto.VaccineResponse;
import com.immuna.service.AdminVaccineService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/vaccines")
public class AdminVaccineController {
	
	@Autowired
	private AdminVaccineService adminVaccineService;
	
	@PostMapping
    public VaccineResponse create(@Valid @RequestBody VaccineRequest request) {
        return adminVaccineService.createVaccine(request);
    }

    @GetMapping
    public List<VaccineResponse> getAll() {
        return adminVaccineService.getAllVaccines();
    }

    @GetMapping("/{id}")
    public VaccineResponse getById(@PathVariable Long id) {
        return adminVaccineService.getVaccineById(id);
    }

    @PutMapping("/{id}")
    public VaccineResponse update(@PathVariable Long id,
                                  @Valid @RequestBody VaccineRequest request) {
        return adminVaccineService.updateVaccine(id, request);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        adminVaccineService.deleteVaccine(id);
        return "Vaccine deleted successfully";
    }

}

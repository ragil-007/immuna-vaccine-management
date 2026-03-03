package com.immuna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.immuna.dto.VaccineLibraryResponse;
import com.immuna.service.VaccineLibraryService;

@RestController
@RequestMapping("/api/library/vaccines")
public class VaccineLibraryController {
	
	@Autowired
	private VaccineLibraryService vaccineLibraryService;

    public VaccineLibraryController(VaccineLibraryService vaccineLibraryService) {
        this.vaccineLibraryService = vaccineLibraryService;
    }

    @GetMapping
    public List<VaccineLibraryResponse> getAll(
            @RequestParam(required = false) String search) {

        if (search != null && !search.isBlank()) {
            return vaccineLibraryService.searchVaccines(search);
        }

        return vaccineLibraryService.getAllVaccines();
    }

    @GetMapping("/{id}")
    public VaccineLibraryResponse getById(@PathVariable Long id) {
        return vaccineLibraryService.getVaccineById(id);
    }
	
	

}

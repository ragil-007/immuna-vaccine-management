package com.immuna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.immuna.dto.AdminHomeSummary;
import com.immuna.service.AdminHomeService;

@RestController
@RequestMapping("/api/admin/home")
public class AdminHomeController {
	
	@Autowired
	private AdminHomeService adminHomeService;
	
	public AdminHomeController(AdminHomeService adminHomeService) {
        this.adminHomeService = adminHomeService;
    }

    @GetMapping
    public AdminHomeSummary getSummary() {
        return adminHomeService.getSummary();
    }

}

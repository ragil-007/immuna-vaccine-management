package com.immuna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.immuna.dto.UserResponse;
import com.immuna.service.AdminUserService;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {
	
	@Autowired
	private AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return adminUserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return adminUserService.getUserById(id);
    }
    
    @GetMapping("/normal")
    public List<UserResponse> getNormalUsers() {
        return adminUserService.getNormalUsers();
    }

    @PutMapping("/{id}/disable")
    public String disable(@PathVariable Long id) {
        adminUserService.disableUser(id);
        return "User disabled";
    }

    @PutMapping("/{id}/enable")
    public String enable(@PathVariable Long id) {
        adminUserService.enableUser(id);
        return "User enabled";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        adminUserService.deleteUser(id);
        return "User deleted";
    }

}

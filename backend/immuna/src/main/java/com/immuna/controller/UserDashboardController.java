package com.immuna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.immuna.dto.DashboardResponse;
import com.immuna.entity.User;
import com.immuna.service.UserDashboardService;

@RestController
@RequestMapping("/api/user/dashboard")
public class UserDashboardController {
	
	@Autowired
	private UserDashboardService userDashboardService;

    public UserDashboardController(UserDashboardService userDashboardService) {
        this.userDashboardService = userDashboardService;
    }

    @GetMapping
    public DashboardResponse getDashboard(Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        return userDashboardService.getDashboard(user.getUserId());
    }

}

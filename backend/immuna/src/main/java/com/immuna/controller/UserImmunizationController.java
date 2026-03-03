package com.immuna.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.immuna.dto.UserImmunizationResponse;
import com.immuna.entity.User;
import com.immuna.service.UserImmunizationService;

@RestController
@RequestMapping("/api/user/immunizations")
public class UserImmunizationController {
	
	private final UserImmunizationService userImmunizationService;

    public UserImmunizationController(UserImmunizationService userImmunizationService) {
        this.userImmunizationService = userImmunizationService;
    }

    private Long getUserId(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return user.getUserId();
    }

    @GetMapping("/history")
    public List<UserImmunizationResponse> history(Authentication authentication) {
        Long userId = getUserId(authentication);
        return userImmunizationService.getVaccinationHistory(userId);
    }

    @GetMapping("/upcoming")
    public List<UserImmunizationResponse> upcoming(Authentication authentication) {
        Long userId = getUserId(authentication);
        return userImmunizationService.getUpcomingDoses(userId);
    }

    @GetMapping("/overdue")
    public List<UserImmunizationResponse> overdue(Authentication authentication) {
        Long userId = getUserId(authentication);
        return userImmunizationService.getOverdueDoses(userId);
    }

}

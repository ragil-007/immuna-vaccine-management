package com.immuna.service;

import com.immuna.dto.DashboardResponse;

public interface UserDashboardService {
	
	DashboardResponse getDashboard(Long userId);

}

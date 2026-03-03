package com.immuna.service;

import java.util.List;

import com.immuna.dto.UserResponse;

public interface AdminUserService {
	
	List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    void disableUser(Long id);

    void enableUser(Long id);

    void deleteUser(Long id);

	List<UserResponse> getNormalUsers();

}

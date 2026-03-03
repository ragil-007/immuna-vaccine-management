package com.immuna.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immuna.dto.UserResponse;
import com.immuna.entity.Role;
import com.immuna.entity.User;
import com.immuna.exception.AdminOperationNotAllowedException;
import com.immuna.exception.ResourceNotFoundException;
import com.immuna.repository.ImmunizationRecordRepository;
import com.immuna.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminUserServiceImpl implements AdminUserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private ImmunizationRecordRepository recordRepository;

	@Override
	public List<UserResponse> getAllUsers() {
		return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
	}

	@Override
	public UserResponse getUserById(Long id) {
		User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return mapToResponse(user);
	}

	@Override
	@Transactional
	public void disableUser(Long id) {

	    User user = userRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

	    if (user.getRole().name().equals("ADMIN")) {
	        throw new AdminOperationNotAllowedException("Admin account cannot be disabled");
	    }

	    user.setIsActive(false);
	    userRepository.save(user);
	}

	@Override
	@Transactional
	public void enableUser(Long id) {

	    User user = userRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

	    if (user.getRole().name().equals("ADMIN")) {
	        throw new AdminOperationNotAllowedException("Admin account is always active");
	    }

	    user.setIsActive(true);
	    userRepository.save(user);
	}

	@Override
	@Transactional
	public void deleteUser(Long id) {

	    User user = userRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

	    if (user.getRole().name().equals("ADMIN")) {
	        throw new AdminOperationNotAllowedException("Admin account cannot be deleted");
	    }

	    userRepository.delete(user);
	}
	
	private UserResponse mapToResponse(User user) {

        long totalVaccinations =
                recordRepository.countByUser(user);

        return new UserResponse(
                user.getUserId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole().name(),
                user.getIsActive(),
                totalVaccinations
        );
    }

	@Override
	public List<UserResponse> getNormalUsers() {
		return userRepository.findByRole(Role.USER)
	            .stream()
	            .map(this::mapToResponse)
	            .toList();
	}

}

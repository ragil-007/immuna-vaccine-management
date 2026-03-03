package com.immuna.dto;

public class UserResponse {

	private Long userId;
	private String fullName;
	private String email;
	private String role;
	private Boolean isActive;
	private Long totalVaccinations;

	public UserResponse(Long userId, String fullName, String email, 
			String role, Boolean isActive,Long totalVaccinations) {
		this.userId = userId;
		this.fullName = fullName;
		this.email = email;
		this.role = role;
		this.isActive = isActive;
		this.totalVaccinations = totalVaccinations;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getTotalVaccinations() {
		return totalVaccinations;
	}

	public void setTotalVaccinations(Long totalVaccinations) {
		this.totalVaccinations = totalVaccinations;
	}

}

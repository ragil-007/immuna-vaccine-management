package com.immuna.dto;

public class AdminHomeSummary {
	
	private long totalUsers;
    private long totalVaccines;
    private long totalDiseases;
    private long totalImmunizationRecords;
    
	public AdminHomeSummary(long totalUsers, long totalVaccines, 
			long totalDiseases, long totalImmunizationRecords) {
		this.totalUsers = totalUsers;
		this.totalVaccines = totalVaccines;
		this.totalDiseases = totalDiseases;
		this.totalImmunizationRecords = totalImmunizationRecords;
	}

	public long getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(long totalUsers) {
		this.totalUsers = totalUsers;
	}

	public long getTotalVaccines() {
		return totalVaccines;
	}

	public void setTotalVaccines(long totalVaccines) {
		this.totalVaccines = totalVaccines;
	}

	public long getTotalDiseases() {
		return totalDiseases;
	}

	public void setTotalDiseases(long totalDiseases) {
		this.totalDiseases = totalDiseases;
	}

	public long getTotalImmunizationRecords() {
		return totalImmunizationRecords;
	}

	public void setTotalImmunizationRecords(long totalImmunizationRecords) {
		this.totalImmunizationRecords = totalImmunizationRecords;
	}
	
    
    

}

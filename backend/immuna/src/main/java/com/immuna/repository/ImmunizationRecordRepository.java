package com.immuna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.immuna.entity.ImmunizationRecord;
import com.immuna.entity.User;
import com.immuna.entity.VaccineSchedule;

public interface ImmunizationRecordRepository extends JpaRepository<ImmunizationRecord, Long> {
	
	List<ImmunizationRecord> findByUser(User user);

	long countByUserAndSchedule(User user, VaccineSchedule schedule);
	
	long countByUser(User user);
	
	List<ImmunizationRecord> findByUserOrderByDateTakenDesc(User user);

}

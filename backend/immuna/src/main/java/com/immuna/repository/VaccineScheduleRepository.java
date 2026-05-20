package com.immuna.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.immuna.entity.Vaccine;
import com.immuna.entity.VaccineSchedule;

public interface VaccineScheduleRepository extends JpaRepository<VaccineSchedule, Long> {

	boolean existsByVaccineAndDoseNo(Vaccine vaccine, Integer doseNo);

	List<VaccineSchedule> findByVaccine_VaccineId(Long vaccineId);

	Optional<VaccineSchedule> findByVaccineAndDoseNo(Vaccine vaccine, Integer doseNo);

}

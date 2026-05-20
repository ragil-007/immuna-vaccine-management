package com.immuna.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immuna.dto.DashboardResponse;
import com.immuna.entity.ImmunizationRecord;
import com.immuna.entity.User;
import com.immuna.exception.ResourceNotFoundException;
import com.immuna.repository.ImmunizationRecordRepository;
import com.immuna.repository.UserRepository;

@Service
public class UserDashboardServiceImpl implements UserDashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImmunizationRecordRepository recordRepository;

    @Override
    public DashboardResponse getDashboard(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        List<ImmunizationRecord> records =
                recordRepository.findByUser(user);

        if (records.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No vaccination records found"
            );
        }

        LocalDate today = LocalDate.now();

        // =====================================================
        // FIND NEXT ACTIVE VACCINATION
        // Ignore completed records (nextDueDate == null)
        // =====================================================

        ImmunizationRecord nextRecord = records.stream()

                // Ignore completed vaccines
                .filter(r -> r.getNextDueDate() != null)

                // Get nearest due date
                .min(Comparator.comparing(
                        ImmunizationRecord::getNextDueDate
                ))

                .orElse(null);

        String status = null;
        long daysRemaining = 0;

        // =====================================================
        // STATUS CALCULATION
        // =====================================================

        if (nextRecord != null) {

            LocalDate nextDate = nextRecord.getNextDueDate();

            daysRemaining =
                    ChronoUnit.DAYS.between(today, nextDate);

            if (nextDate.isBefore(today)) {

                status = "OVERDUE";

                daysRemaining = Math.abs(daysRemaining);

            } else if (nextDate.isEqual(today)) {

                status = "DUE";

            } else {

                status = "UPCOMING";
            }

        } else {

            // All vaccinations completed
            status = "COMPLETED";
        }

        // =====================================================
        // MOST RECENT VACCINATION
        // =====================================================

        ImmunizationRecord recentRecord = records.stream()

                .max(Comparator.comparing(
                        ImmunizationRecord::getDateTaken
                ))

                .orElse(null);

        // =====================================================
        // RESPONSE
        // =====================================================

        return new DashboardResponse(

                // Next vaccination info
                nextRecord != null
                        ? nextRecord.getSchedule()
                                .getVaccine()
                                .getVaccineName()
                        : null,

                nextRecord != null
                        ? nextRecord.getSchedule()
                                .getDoseNo()
                        : null,

                nextRecord != null
                        ? nextRecord.getSchedule()
                                .getDoseType()
                                .name()
                        : null,

                nextRecord != null
                        ? nextRecord.getNextDueDate()
                        : null,

                daysRemaining,

                status,

                // Recent vaccination info
                recentRecord != null
                        ? recentRecord.getSchedule()
                                .getVaccine()
                                .getVaccineName()
                        : null,

                recentRecord != null
                        ? recentRecord.getSchedule()
                                .getDoseNo()
                        : null,

                recentRecord != null
                        ? recentRecord.getDateTaken()
                        : null
        );
    }
}
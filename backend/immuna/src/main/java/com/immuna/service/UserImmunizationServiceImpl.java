package com.immuna.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immuna.dto.UserImmunizationResponse;
import com.immuna.entity.ImmunizationRecord;
import com.immuna.entity.User;
import com.immuna.exception.ResourceNotFoundException;
import com.immuna.repository.ImmunizationRecordRepository;
import com.immuna.repository.UserRepository;

@Service
public class UserImmunizationServiceImpl implements UserImmunizationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImmunizationRecordRepository recordRepository;

    // =====================================================
    // HISTORY
    // Show ALL records
    // =====================================================

    @Override
    public List<UserImmunizationResponse> getVaccinationHistory(Long userId) {

        User user = getUser(userId);

        return recordRepository.findByUserOrderByDateTakenDesc(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // =====================================================
    // UPCOMING
    // ONLY latest dose of each vaccine
    // =====================================================

    @Override
    public List<UserImmunizationResponse> getUpcomingDoses(Long userId) {

        User user = getUser(userId);

        LocalDate today = LocalDate.now();

        return getLatestRecordsPerVaccine(user)

                .stream()

                .filter(r ->
                        r.getNextDueDate() != null &&
                        (
                            r.getNextDueDate().isAfter(today) ||
                            r.getNextDueDate().isEqual(today)
                        )
                )

                .sorted(Comparator.comparing(
                        ImmunizationRecord::getNextDueDate
                ))

                .map(r -> mapToResponseWithStatus(r, "UPCOMING"))

                .collect(Collectors.toList());
    }

    // =====================================================
    // OVERDUE
    // ONLY latest dose of each vaccine
    // =====================================================

    @Override
    public List<UserImmunizationResponse> getOverdueDoses(Long userId) {

        User user = getUser(userId);

        LocalDate today = LocalDate.now();

        return getLatestRecordsPerVaccine(user)

                .stream()

                .filter(r ->
                        r.getNextDueDate() != null &&
                        r.getNextDueDate().isBefore(today)
                )

                .sorted(Comparator.comparing(
                        ImmunizationRecord::getNextDueDate
                ))

                .map(r -> mapToResponseWithStatus(r, "OVERDUE"))

                .collect(Collectors.toList());
    }

    // =====================================================
    // GET ONLY LATEST DOSE OF EACH VACCINE
    // =====================================================

    private List<ImmunizationRecord> getLatestRecordsPerVaccine(User user) {

        List<ImmunizationRecord> allRecords =
                recordRepository.findByUser(user);

        // Group by vaccine name
        Map<String, List<ImmunizationRecord>> grouped =
                allRecords.stream()

                .collect(Collectors.groupingBy(
                        r -> r.getSchedule()
                              .getVaccine()
                              .getVaccineName()
                ));

        // From each vaccine group,
        // get latest taken dose
        return grouped.values()

                .stream()

                .map(records -> records.stream()

                        .max(Comparator.comparing(
                                ImmunizationRecord::getDateTaken
                        ))

                        .orElse(null)
                )

                .filter(r -> r != null)

                .collect(Collectors.toList());
    }

    // =====================================================
    // GET USER
    // =====================================================

    private User getUser(Long userId) {

        return userRepository.findById(userId)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        )
                );
    }

    // =====================================================
    // DEFAULT RESPONSE
    // =====================================================

    private UserImmunizationResponse mapToResponse(
            ImmunizationRecord record
    ) {

        String status = "COMPLETED";

        if (record.getNextDueDate() != null) {

            if (record.getNextDueDate()
                    .isBefore(LocalDate.now())) {

                status = "OVERDUE";

            } else {

                status = "UPCOMING";
            }
        }

        return new UserImmunizationResponse(

                record.getRecordId(),

                record.getSchedule()
                        .getVaccine()
                        .getVaccineName(),

                record.getSchedule()
                        .getDoseNo(),

                record.getSchedule()
                        .getDoseType()
                        .name(),

                record.getDateTaken(),

                record.getNextDueDate(),

                status
        );
    }

    // =====================================================
    // RESPONSE WITH MANUAL STATUS
    // =====================================================

    private UserImmunizationResponse mapToResponseWithStatus(
            ImmunizationRecord record,
            String status
    ) {

        return new UserImmunizationResponse(

                record.getRecordId(),

                record.getSchedule()
                        .getVaccine()
                        .getVaccineName(),

                record.getSchedule()
                        .getDoseNo(),

                record.getSchedule()
                        .getDoseType()
                        .name(),

                record.getDateTaken(),

                record.getNextDueDate(),

                status
        );
    }
}
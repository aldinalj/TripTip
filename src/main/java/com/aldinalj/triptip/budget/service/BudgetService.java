package com.aldinalj.triptip.budget.service;

import com.aldinalj.triptip.budget.model.Budget;
import com.aldinalj.triptip.budget.model.dto.BudgetDTO;
import com.aldinalj.triptip.budget.repository.BudgetRepository;
import com.aldinalj.triptip.config.security.CustomUserDetails;
import com.aldinalj.triptip.trip.model.Trip;
import com.aldinalj.triptip.trip.repository.TripRepository;
import com.aldinalj.triptip.user.model.CustomUser;
import com.aldinalj.triptip.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final TripRepository tripRepository;
    private final UserRepository userRepository;


    @Autowired
    public BudgetService(BudgetRepository budgetRepository, TripRepository tripRepository, UserRepository userRepository) {
        this.budgetRepository = budgetRepository;
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseEntity<BudgetDTO> createBudget(BudgetDTO budgetDTO) {

        if (budgetRepository.findByBudgetNameIgnoreCase(budgetDTO.getBudgetName()).isPresent()) {

            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Budget budget = new Budget(
                budgetDTO.getBudgetName(),
                budgetDTO.getTotal()
        );

            budget.setTrip(tripRepository.findByTripNameIgnoreCase(budgetDTO.getTripName())
                    .orElseThrow(() -> new IllegalArgumentException("Trip not found")));

            budgetRepository.save(budget);

            return ResponseEntity.status(HttpStatus.CREATED).body(budgetDTO);
    }

    @Transactional
    public ResponseEntity<List<Budget>> getBudgetsByTrip(Long tripId, CustomUserDetails userDetails) {

        if (userDetails == null) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        CustomUser user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Trip trip = tripRepository.findByIdAndUserId(tripId, user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Trip not found"));

        List<Budget> budgets = budgetRepository.findAllByTripId(trip.getId());

        return ResponseEntity.ok(budgets);
    }

    @Transactional
    public ResponseEntity<Budget> getBudget(Long budgetId, Long tripId,  CustomUserDetails userDetails) {

        if (userDetails == null) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        CustomUser user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Trip trip = tripRepository.findByIdAndUserId(tripId, user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Trip not found"));

        Budget budget = budgetRepository.findByIdAndTripId(budgetId, trip.getId())
                .orElseThrow(() -> new IllegalArgumentException("Budget not found"));

        return ResponseEntity.ok(budget);
    }
}

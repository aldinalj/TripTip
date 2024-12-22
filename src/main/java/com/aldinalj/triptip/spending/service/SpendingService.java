package com.aldinalj.triptip.spending.service;

import com.aldinalj.triptip.budget.model.Budget;
import com.aldinalj.triptip.budget.repository.BudgetRepository;
import com.aldinalj.triptip.config.security.CustomUserDetails;
import com.aldinalj.triptip.spending.model.Spending;
import com.aldinalj.triptip.spending.model.dto.SpendingDTO;
import com.aldinalj.triptip.spending.repository.SpendingRepository;
import com.aldinalj.triptip.trip.model.Trip;
import com.aldinalj.triptip.trip.repository.TripRepository;
import com.aldinalj.triptip.user.model.CustomUser;
import com.aldinalj.triptip.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpendingService {

    private final SpendingRepository spendingRepository;
    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final TripRepository tripRepository;

    @Autowired
    public SpendingService(SpendingRepository spendingRepository, BudgetRepository budgetRepository, UserRepository userRepository, TripRepository tripRepository) {
        this.spendingRepository = spendingRepository;
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
        this.tripRepository = tripRepository;
    }

    @Transactional
    public ResponseEntity<SpendingDTO> createSpending(SpendingDTO spendingDTO) {

        Spending spending = new Spending(
                spendingDTO.getSpendingName(),
                spendingDTO.getDescription(),
                spendingDTO.getMoneySpent()
        );

        spending.setBudget(budgetRepository.findByBudgetNameIgnoreCase(spendingDTO.getBudgetName())
                .orElseThrow(() -> new IllegalArgumentException("Budget not found")));

        spendingRepository.save(spending);

        return ResponseEntity.status(HttpStatus.CREATED).body(spendingDTO);
    }

    @Transactional
    public ResponseEntity<List<Spending>> getAllSpendings(
            Long budgetId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {

        if (userDetails == null) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        CustomUser user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new IllegalArgumentException("Budget not found"));

        Trip trip = tripRepository.findByIdAndUserId(budget.getTrip().getId(), user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Trip not found"));

        List<Spending> spendings = spendingRepository.findAllByBudgetId(budgetId);


        return ResponseEntity.ok(spendings);
    }
}

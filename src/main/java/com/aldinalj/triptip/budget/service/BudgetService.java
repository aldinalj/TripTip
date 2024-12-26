package com.aldinalj.triptip.budget.service;

import com.aldinalj.triptip.budget.model.Budget;
import com.aldinalj.triptip.budget.model.dto.BudgetDTO;
import com.aldinalj.triptip.budget.repository.BudgetRepository;
import com.aldinalj.triptip.config.security.CustomUserDetails;
import com.aldinalj.triptip.spending.model.Spending;
import com.aldinalj.triptip.spending.repository.SpendingRepository;
import com.aldinalj.triptip.trip.repository.TripRepository;
import com.aldinalj.triptip.user.model.CustomUser;
import com.aldinalj.triptip.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final TripRepository tripRepository;
    private final UserRepository userRepository;
    private final SpendingRepository spendingRepository;


    @Autowired
    public BudgetService(BudgetRepository budgetRepository, TripRepository tripRepository, UserRepository userRepository, SpendingRepository spendingRepository) {
        this.budgetRepository = budgetRepository;
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
        this.spendingRepository = spendingRepository;
    }

    @Transactional
    public ResponseEntity<BudgetDTO> createBudget(BudgetDTO budgetDTO) {

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

        CustomUser user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Budget> budgets = budgetRepository.findAllByTripIdAndUserId(tripId, user.getId());

        if (budgets.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(budgets);
    }



    @Transactional
    public ResponseEntity<Budget> getBudget(Long budgetId, Long tripId,  CustomUserDetails userDetails) {

        CustomUser user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Budget budget = budgetRepository.findByIdAndTripIdAndUserId(budgetId, tripId, user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Budget not found"));

        return ResponseEntity.ok(budget);
    }

    @Transactional
    public ResponseEntity<Map<String, Object>> getBudgetSummary(Long budgetId, CustomUserDetails userDetails) {

        CustomUser user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Budget budget = budgetRepository.findByIdAndUserId(budgetId, user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Budget not found or not authorized"));

        List<Spending> spendings = spendingRepository.findAllByBudgetId(budgetId);

        double moneySpent = spendings.stream()
                .mapToDouble(Spending::getMoneySpent)
                .sum();

        Map<String, Object> response = Map.of(
                "spendings", spendings,
                "moneySpent", moneySpent,
                "budgetTotal", budget.getTotal()
        );

        return ResponseEntity.ok(response);
    }


    @Transactional
    public ResponseEntity<Map<String, Object>> getAllBudgetSummaries(
            Long tripId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {

        CustomUser user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Budget> budgets = budgetRepository.findAllByTripIdAndUserId(tripId, user.getId());

        List<Map<String, Object>> budgetDetailsList = new ArrayList<>();

        for (Budget budget : budgets) {

            List<Spending> spendings = spendingRepository.findAllByBudgetId(budget.getId());

            double moneySpent = spendings.stream()
                    .mapToDouble(Spending::getMoneySpent)
                    .sum();

            Map<String, Object> budgetDetails = new HashMap<>();
            budgetDetails.put("budgetId", budget.getId());
            budgetDetails.put("budgetName", budget.getBudgetName());
            budgetDetails.put("moneySpent", moneySpent);
            budgetDetails.put("budgetTotal", budget.getTotal());

            budgetDetailsList.add(budgetDetails);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("budgets", budgetDetailsList);

        return ResponseEntity.ok(response);
    }

    @Transactional
    public ResponseEntity<List<Budget>> getAllBudgets(CustomUserDetails userDetails) {

        CustomUser user = userRepository.findByUsername(userDetails.getUsername())
                        .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Budget> budgets = budgetRepository.findAllBudgets(user.getId());

        return ResponseEntity.ok(budgets);
    }
}


package com.aldinalj.triptip.spending.service;

import com.aldinalj.triptip.budget.model.Budget;
import com.aldinalj.triptip.budget.repository.BudgetRepository;
import com.aldinalj.triptip.config.security.CustomUserDetails;
import com.aldinalj.triptip.spending.model.Spending;
import com.aldinalj.triptip.spending.model.dto.SpendingDTO;
import com.aldinalj.triptip.spending.repository.SpendingRepository;
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

    @Autowired
    public SpendingService(SpendingRepository spendingRepository, BudgetRepository budgetRepository, UserRepository userRepository) {
        this.spendingRepository = spendingRepository;
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseEntity<SpendingDTO> createSpending(SpendingDTO spendingDTO, CustomUserDetails userDetails) {

        CustomUser user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Budget budget = budgetRepository.findByBudgetIdAndUserId(spendingDTO.getBudgetId(), user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Budget not found"));

        Spending spending = new Spending(
                spendingDTO.getSpendingName(),
                spendingDTO.getDescription(),
                spendingDTO.getMoneySpent()
        );

        spending.setBudget(budget);
        spendingRepository.save(spending);

        return ResponseEntity.status(HttpStatus.CREATED).body(spendingDTO);
    }

    @Transactional
    public ResponseEntity<List<Spending>> getAllSpendingsByBudget(
            Long budgetId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {

        CustomUser user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Spending> spendings = spendingRepository.findAllByBudgetIdAndUserId(budgetId, user.getId());

        return ResponseEntity.ok(spendings);
    }


    @Transactional
    public ResponseEntity<Spending> getSpending(Long spendingId, CustomUserDetails userDetails) {


        CustomUser user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Spending spending = spendingRepository.findByIdAndUserId(spendingId, user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Spending not found"));

        return ResponseEntity.ok(spending);
    }

    @Transactional
    public ResponseEntity<List<Spending>> getAllSpendings(CustomUserDetails userDetails) {

        CustomUser user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Spending> spendings = spendingRepository.findAllSpendings(user.getId());

        return ResponseEntity.ok(spendings);
    }
}
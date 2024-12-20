package com.aldinalj.triptip.spending.service;

import com.aldinalj.triptip.budget.model.Budget;
import com.aldinalj.triptip.budget.repository.BudgetRepository;
import com.aldinalj.triptip.spending.model.Spending;
import com.aldinalj.triptip.spending.model.dto.SpendingDTO;
import com.aldinalj.triptip.spending.repository.SpendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpendingService {

    private final SpendingRepository spendingRepository;
    private final BudgetRepository budgetRepository;

    @Autowired
    public SpendingService(SpendingRepository spendingRepository, BudgetRepository budgetRepository) {
        this.spendingRepository = spendingRepository;
        this.budgetRepository = budgetRepository;
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
    public ResponseEntity<List<Spending>> getAllSpendings(String budgetName) {

        Long budgetId = budgetRepository.findIdByBudgetName(budgetName)
                .orElseThrow(() -> new IllegalArgumentException("Budget not found"));

        List<Spending> spendings = spendingRepository.findByBudgetId(budgetId);

        return ResponseEntity.ok(spendings);
    }
}

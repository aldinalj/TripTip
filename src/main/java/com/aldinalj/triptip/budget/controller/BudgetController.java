package com.aldinalj.triptip.budget.controller;

import com.aldinalj.triptip.budget.model.Budget;
import com.aldinalj.triptip.budget.model.dto.BudgetDTO;
import com.aldinalj.triptip.budget.service.BudgetService;
import com.aldinalj.triptip.config.security.CustomUserDetails;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping("/create")
    public ResponseEntity<BudgetDTO> createBudget(@Valid @RequestBody BudgetDTO budgetDTO) {

        return budgetService.createBudget(budgetDTO);
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<List<Budget>> getBudgetsByTrip(@PathVariable Long tripId, @AuthenticationPrincipal CustomUserDetails userDetails) {

        return budgetService.getBudgetsByTrip(tripId, userDetails);
    }

    @GetMapping("/budget/{budgetId}")
    public ResponseEntity<Budget> getBudget(@PathVariable Long budgetId, @RequestParam Long tripId, @AuthenticationPrincipal CustomUserDetails userDetails) {

        return budgetService.getBudget(budgetId, tripId, userDetails);
    }
}

package com.aldinalj.triptip.budget.controller;

import com.aldinalj.triptip.budget.model.dto.BudgetDTO;
import com.aldinalj.triptip.budget.service.BudgetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budget")
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
}

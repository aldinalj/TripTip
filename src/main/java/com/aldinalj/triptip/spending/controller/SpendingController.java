package com.aldinalj.triptip.spending.controller;

import com.aldinalj.triptip.budget.model.Budget;
import com.aldinalj.triptip.spending.model.Spending;
import com.aldinalj.triptip.spending.model.dto.SpendingDTO;
import com.aldinalj.triptip.spending.service.SpendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spendings")
public class SpendingController {

    private final SpendingService spendingService;

    @Autowired
    public SpendingController(SpendingService spendingService) {
        this.spendingService = spendingService;
    }

    @PostMapping("/create")
    public ResponseEntity<SpendingDTO> createSpending(@RequestBody SpendingDTO spendingDTO) {

        return spendingService.createSpending(spendingDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Spending>> getAllSpendings(@RequestParam ("budgetName") String budgetName) {

        return spendingService.getAllSpendings(budgetName);
    }
}

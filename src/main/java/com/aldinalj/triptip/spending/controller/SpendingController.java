package com.aldinalj.triptip.spending.controller;

import com.aldinalj.triptip.config.security.CustomUserDetails;
import com.aldinalj.triptip.spending.model.Spending;
import com.aldinalj.triptip.spending.model.dto.SpendingDTO;
import com.aldinalj.triptip.spending.service.SpendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/{budgetId}")
    public ResponseEntity<List<Spending>> getAllSpendingsByBudget(
            @PathVariable Long budgetId,
            @AuthenticationPrincipal CustomUserDetails userDetails
            ) {

        return spendingService.getAllSpendingsByBudget(budgetId, userDetails);
    }

    @GetMapping("/spending/{spendingId}")
    public ResponseEntity<Spending> getSpending(
            @PathVariable Long spendingId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {

        return spendingService.getSpending(spendingId, userDetails);
    }

    @GetMapping
    public ResponseEntity<List<Spending>> getAllSpendings(@AuthenticationPrincipal CustomUserDetails userDetails) {

        return spendingService.getAllSpendings(userDetails);
    }
}

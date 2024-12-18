package com.aldinalj.triptip.spending.controller;

import com.aldinalj.triptip.spending.model.dto.SpendingDTO;
import com.aldinalj.triptip.spending.service.SpendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spending")
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
}

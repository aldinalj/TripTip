package com.aldinalj.triptip.budget.service;

import com.aldinalj.triptip.budget.model.Budget;
import com.aldinalj.triptip.budget.model.dto.BudgetDTO;
import com.aldinalj.triptip.budget.repository.BudgetRepository;
import com.aldinalj.triptip.trip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final TripRepository tripRepository;


    @Autowired
    public BudgetService(BudgetRepository budgetRepository, TripRepository tripRepository) {
        this.budgetRepository = budgetRepository;
        this.tripRepository = tripRepository;
    }

    @Transactional
    public ResponseEntity<BudgetDTO> createBudget(BudgetDTO budgetDTO) {

        Budget budget = new Budget(
                budgetDTO.getBudgetName(),
                budgetDTO.getTotal()
        );

            budget.setTrip(tripRepository.findByTripName(budgetDTO.getTripName())
                    .orElseThrow(() -> new IllegalArgumentException("Trip not found")));

            budgetRepository.save(budget);

            return ResponseEntity.status(HttpStatus.CREATED).body(budgetDTO);
    }
}

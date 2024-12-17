package com.aldinalj.triptip.budget.repository;

import com.aldinalj.triptip.budget.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {




}

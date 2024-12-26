package com.aldinalj.triptip.budget.repository;

import com.aldinalj.triptip.budget.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {

    Optional<Budget> findByBudgetNameIgnoreCase(String budgetName);

    List<Budget> findAllByTripId(Long tripId);

    Optional<Budget> findById(Long id);

    Optional<Budget> findByIdAndTripId(Long budgetId, Long tripId);

    @Query(value = """
    SELECT b 
    FROM Trip t 
    JOIN t.budgets b 
    WHERE t.user.id = :userId
""")
    List<Budget> findAllBudgets(@Param("userId")Long userId);
}

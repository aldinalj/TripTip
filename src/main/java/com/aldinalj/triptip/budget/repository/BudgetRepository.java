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


    @Query(value = """
    SELECT b 
    FROM Trip t 
    JOIN t.budgets b 
    WHERE t.user.id = :userId
""")
    List<Budget> findAllBudgets(@Param("userId")Long userId);

    @Query("""
    SELECT b 
    FROM Budget b 
    JOIN b.trip t 
    WHERE t.id = :tripId AND t.user.id = :userId
""")
    List<Budget> findAllByTripIdAndUserId(@Param("tripId") Long tripId, @Param("userId") Long userId);

    @Query("""
    SELECT b 
    FROM Budget b 
    JOIN b.trip t 
    WHERE b.id = :budgetId AND t.id = :tripId AND t.user.id = :userId
""")
    Optional<Budget> findByIdAndTripIdAndUserId(
            @Param("budgetId") Long budgetId,
            @Param("tripId") Long tripId,
            @Param("userId") Long userId
    );

    @Query("""
    SELECT b 
    FROM Budget b 
    JOIN b.trip t 
    WHERE b.id = :budgetId AND t.user.id = :userId
""")
    Optional<Budget> findByIdAndUserId(@Param("budgetId") Long budgetId, @Param("userId") Long userId);
}

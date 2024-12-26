package com.aldinalj.triptip.spending.repository;

import com.aldinalj.triptip.spending.model.Spending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpendingRepository extends JpaRepository<Spending, Integer> {

    List<Spending> findAllByBudgetId(Long budgetId);

    Optional<Spending> findByIdAndBudgetId(Long spendingId, Long budgetId);

    @Query(value = """
    SELECT s 
    FROM Trip t 
    JOIN t.budgets b 
    JOIN b.spendings s 
    WHERE t.user.id = :userId
""")
    List<Spending> findAllSpendings(@Param("userId")Long userId);
}

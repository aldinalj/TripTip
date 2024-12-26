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

    @Query(value = """
    SELECT s 
    FROM Trip t 
    JOIN t.budgets b 
    JOIN b.spendings s 
    WHERE t.user.id = :userId
""")
    List<Spending> findAllSpendings(@Param("userId")Long userId);

    @Query("SELECT s FROM Spending s WHERE s.budget.id = :budgetId")
    List<Spending> findAllByBudgetId(@Param("budgetId") Long budgetId);

    @Query("SELECT s FROM Spending s JOIN s.budget b JOIN b.trip t WHERE s.id = :spendingId AND t.user.id = :userId")
    Optional<Spending> findByIdAndUserId(Long spendingId, Long userId);

    @Query("SELECT s FROM Spending s JOIN s.budget b JOIN b.trip t WHERE b.id = :budgetId AND t.user.id = :userId")
    List<Spending> findAllByBudgetIdAndUserId(Long budgetId, Long userId);
}

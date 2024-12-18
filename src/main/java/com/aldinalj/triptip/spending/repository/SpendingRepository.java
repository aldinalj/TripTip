package com.aldinalj.triptip.spending.repository;

import com.aldinalj.triptip.spending.model.Spending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpendingRepository extends JpaRepository<Spending, Integer> {
}

package com.smart_budget_tracker.bil482.repository;

import com.smart_budget_tracker.bil482.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Çağan Durgun
 */
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}

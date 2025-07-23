package com.smart_budget_tracker.bil482.usecase_2.strategy;

import com.smart_budget_tracker.bil482.entity.Expense;
import com.smart_budget_tracker.bil482.usecase_2.budget.Budget;
import java.util.List;

public interface BudgetingStrategy {
    boolean checkBudget(Budget budget, List<Expense> expenses, Expense newExpense);
    String getStrategyName();
}
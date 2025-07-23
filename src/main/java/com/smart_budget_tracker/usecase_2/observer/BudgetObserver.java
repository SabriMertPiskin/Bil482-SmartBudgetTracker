package com.smart_budget_tracker.bil482.usecase_2.observer;

import com.smart_budget_tracker.bil482.entity.Expense;

public interface BudgetObserver {
    void update(Expense expense);
}
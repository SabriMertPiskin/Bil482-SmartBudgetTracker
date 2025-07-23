/**package com.smart_budget_tracker.bil482.usecase_2.strategy;

import com.smart_budget_tracker.bil482.entity.Expense;
import com.smart_budget_tracker.bil482.usecase_2.budget.Budget;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class MonthlyBudgetStrategy implements BudgetingStrategy {
    @Override
    public boolean checkBudget(Budget budget, List<Expense> expenses, Expense newExpense) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfMonth = now.withDayOfMonth(1).toLocalDate().atStartOfDay();
        LocalDateTime endOfMonth = now.withDayOfMonth(now.toLocalDate().lengthOfMonth()).toLocalDate().atTime(23, 59, 59);

        BigDecimal totalSpent = expenses.stream()
            .filter(e -> e.getCategory().equals(budget.getCategory()) && !e.getDate().isBefore(startOfMonth) && !e.getDate().isAfter(endOfMonth))
            .map(Expense::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        totalSpent = totalSpent.add(newExpense.getAmount());

        return totalSpent.compareTo(budget.getAmount()) > 0;
    }

    @Override
    public String getStrategyName() {
        return "MONTHLY";
    }
}*/

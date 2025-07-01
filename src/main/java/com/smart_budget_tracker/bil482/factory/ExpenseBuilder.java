package com.smart_budget_tracker.bil482.factory;

import com.smart_budget_tracker.bil482.entity.Expense;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Çağan Durgun
 */
public abstract class ExpenseBuilder {
    protected Expense expense;

    public ExpenseBuilder() {
        this.expense = new Expense();
    }

    public abstract ExpenseBuilder setDefaultCategory();

    public abstract ExpenseBuilder setValidationRules();

    public abstract String getExpenseType();

    public ExpenseBuilder setTitle(String title) {
        expense.setTitle(title);
        return this;
    }

    public ExpenseBuilder setAmount(BigDecimal amount) {
        expense.setAmount(amount);
        return this;
    }

    public ExpenseBuilder setDate(LocalDateTime date) {
        expense.setDate(date);
        return this;
    }

    public ExpenseBuilder setCategory(String category) {
        expense.setCategory(category);
        return this;
    }

    public Expense build() {
        // Final validasyonlar
        if (expense.getTitle() == null || expense.getTitle().trim().isEmpty()) {
            expense.setTitle("Başlıksız Harcama");
        }
        if (expense.getDate() == null) {
            expense.setDate(LocalDateTime.now());
        }
        return expense;
    }
}

package com.smart_budget_tracker.bil482.factory;

public class FixedExpenseBuilder extends ExpenseBuilder {

    @Override
    public ExpenseBuilder setDefaultCategory() {
        if (expense.getCategory() == null || expense.getCategory().trim().isEmpty()) {
            expense.setCategory("Sabit Gider");
        }
        return this;
    }

    @Override
    public ExpenseBuilder setValidationRules() {
        // Sabit giderler için özel validasyon kuralları
        if (expense.getAmount() != null && expense.getAmount().doubleValue() <= 0) {
            throw new IllegalArgumentException("Sabit gider tutarı pozitif olmalıdır");
        }
        if (expense.getTitle() != null && expense.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Sabit gider başlığı boş olamaz");
        }
        return this;
    }

    @Override
    public String getExpenseType() {
        return "FIXED";
    }
}

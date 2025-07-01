package com.smart_budget_tracker.bil482.factory;

public class RecurringExpenseBuilder extends ExpenseBuilder {

    @Override
    public ExpenseBuilder setDefaultCategory() {
        if (expense.getCategory() == null || expense.getCategory().trim().isEmpty()) {
            expense.setCategory("Tekrarlayan Gider");
        }
        return this;
    }

    @Override
    public ExpenseBuilder setValidationRules() {
        // Tekrarlayan giderler için özel validasyon kuralları
        if (expense.getAmount() != null && expense.getAmount().doubleValue() <= 0) {
            throw new IllegalArgumentException("Tekrarlayan gider tutarı pozitif olmalıdır");
        }
        if (expense.getTitle() == null || expense.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Tekrarlayan gider başlığı zorunludur");
        }
        return this;
    }

    @Override
    public String getExpenseType() {
        return "RECURRING";
    }
}
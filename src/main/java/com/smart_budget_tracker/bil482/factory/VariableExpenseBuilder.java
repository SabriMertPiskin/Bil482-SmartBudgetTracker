package com.smart_budget_tracker.bil482.factory;

/**
 * @author Çağan Durgun
 */
public class VariableExpenseBuilder extends ExpenseBuilder {

    @Override
    public ExpenseBuilder setDefaultCategory() {
        if (expense.getCategory() == null || expense.getCategory().trim().isEmpty()) {
            expense.setCategory("Değişken Gider");
        }
        return this;
    }

    @Override
    public ExpenseBuilder setValidationRules() {
        // Değişken giderler için özel validasyon kuralları
        if (expense.getAmount() != null && expense.getAmount().doubleValue() < 0) {
            throw new IllegalArgumentException("Değişken gider tutarı negatif olamaz");
        }
        return this;
    }

    @Override
    public String getExpenseType() {
        return "VARIABLE";
    }
}
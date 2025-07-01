package com.smart_budget_tracker.bil482.factory;

import org.springframework.stereotype.Component;

/**
 * @author Çağan Durgun
 */
@Component
public class ExpenseFactory {

    public ExpenseBuilder createExpenseBuilder(String category) {
        // Kategori bazlı factory method
        if (category == null || category.trim().isEmpty()) {
            return new VariableExpenseBuilder();
        }

        String lowerCategory = category.toLowerCase().trim();

        // Sabit giderler (Fixed Expenses)
        if (lowerCategory.contains("kira") || lowerCategory.contains("rent") ||
                lowerCategory.contains("sigorta") || lowerCategory.contains("insurance") ||
                lowerCategory.contains("kredi") || lowerCategory.contains("loan") ||
                lowerCategory.contains("abonelik") || lowerCategory.contains("subscription") ||
                lowerCategory.equals("fixed") || lowerCategory.equals("sabit")) {
            return new FixedExpenseBuilder();
        }
        // Tekrarlayan giderler (Recurring Expenses)
        else if (lowerCategory.contains("aylık") || lowerCategory.contains("monthly") ||
                lowerCategory.contains("haftalık") || lowerCategory.contains("weekly") ||
                lowerCategory.contains("günlük") || lowerCategory.contains("daily") ||
                lowerCategory.contains("recurring") || lowerCategory.contains("tekrarlayan")) {
            return new RecurringExpenseBuilder();
        }
        // Değişken giderler (Variable Expenses) - Default
        else {
            return new VariableExpenseBuilder();
        }
    }

    // Overloaded method for direct category type specification
    public ExpenseBuilder createExpenseBuilderByType(String expenseType) {
        if (expenseType == null) {
            return new VariableExpenseBuilder();
        }

        switch (expenseType.toUpperCase()) {
            case "FIXED":
            case "SABIT":
                return new FixedExpenseBuilder();
            case "VARIABLE":
            case "DEĞIŞKEN":
                return new VariableExpenseBuilder();
            case "RECURRING":
            case "TEKRARLAYAN":
                return new RecurringExpenseBuilder();
            default:
                return new VariableExpenseBuilder();
        }
    }

    // Helper method to determine expense type from category
    public String determineExpenseType(String category) {
        if (category == null || category.trim().isEmpty()) {
            return "VARIABLE";
        }

        String lowerCategory = category.toLowerCase().trim();

        if (lowerCategory.contains("kira") || lowerCategory.contains("rent") ||
                lowerCategory.contains("sigorta") || lowerCategory.contains("insurance") ||
                lowerCategory.contains("kredi") || lowerCategory.contains("loan") ||
                lowerCategory.contains("abonelik") || lowerCategory.contains("subscription") ||
                lowerCategory.equals("fixed") || lowerCategory.equals("sabit")) {
            return "FIXED";
        } else if (lowerCategory.contains("aylık") || lowerCategory.contains("monthly") ||
                lowerCategory.contains("haftalık") || lowerCategory.contains("weekly") ||
                lowerCategory.contains("günlük") || lowerCategory.contains("daily") ||
                lowerCategory.contains("recurring") || lowerCategory.contains("tekrarlayan")) {
            return "RECURRING";
        } else {
            return "VARIABLE";
        }
    }
}
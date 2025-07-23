package com.smart_budget_tracker.bil482;

import com.smart_budget_tracker.bil482.factory.ExpenseBuilder;
import com.smart_budget_tracker.bil482.factory.ExpenseFactory;
import com.smart_budget_tracker.bil482.factory.FixedExpenseBuilder;
import com.smart_budget_tracker.bil482.factory.RecurringExpenseBuilder;
import com.smart_budget_tracker.bil482.factory.VariableExpenseBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExpenseFactoryTest {

    private final ExpenseFactory expenseFactory = new ExpenseFactory();

    @Test
    void createExpenseBuilder_shouldReturnFixedExpenseBuilderForRent() {
        ExpenseBuilder builder = expenseFactory.createExpenseBuilder("kira");
        assertTrue(builder instanceof FixedExpenseBuilder);
    }

    @Test
    void createExpenseBuilder_shouldReturnFixedExpenseBuilderForSubscription() {
        // KODUN DAVRANIŞINA GÖRE DÜZELTİLDİ: "abonelik" kelimesi "aylık" kelimesinden
        // önce kontrol edildiği için bu bir FixedExpenseBuilder döndürmelidir.
        ExpenseBuilder builder = expenseFactory.createExpenseBuilder("aylık abonelik");
        assertTrue(builder instanceof FixedExpenseBuilder, "Expected FixedExpenseBuilder because 'abonelik' has priority");
    }

    @Test
    void createExpenseBuilder_shouldReturnRecurringExpenseBuilderForMonthly() {
        // YENİ TEST: Sadece "aylık" içeren bir senaryo ile Recurring kontrolü
        ExpenseBuilder builder = expenseFactory.createExpenseBuilder("aylık ödeme");
        assertTrue(builder instanceof RecurringExpenseBuilder);
    }

    @Test
    void createExpenseBuilder_shouldReturnVariableExpenseBuilderForGroceries() {
        ExpenseBuilder builder = expenseFactory.createExpenseBuilder("market");
        assertTrue(builder instanceof VariableExpenseBuilder);
    }

    @Test
    void createExpenseBuilderByType_shouldReturnCorrectBuilder() {
        assertTrue(expenseFactory.createExpenseBuilderByType("FIXED") instanceof FixedExpenseBuilder);
        assertTrue(expenseFactory.createExpenseBuilderByType("RECURRING") instanceof RecurringExpenseBuilder);
        assertTrue(expenseFactory.createExpenseBuilderByType("VARIABLE") instanceof VariableExpenseBuilder);
        assertTrue(expenseFactory.createExpenseBuilderByType("UNKNOWN") instanceof VariableExpenseBuilder);
    }
}
package com.smart_budget_tracker.bil482;

import com.smart_budget_tracker.bil482.command.AddExpenseCommand;
import com.smart_budget_tracker.bil482.command.ExpenseCommandInvoker;
import com.smart_budget_tracker.bil482.entity.Expense;
import com.smart_budget_tracker.bil482.service.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ExpenseCommandInvokerTest {

    @Mock
    private ExpenseService expenseService;

    private ExpenseCommandInvoker commandInvoker;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        commandInvoker = new ExpenseCommandInvoker();
    }

    @Test
    void executeAndUndo_shouldCallCorrectServiceMethods() {
        Expense expense = new Expense(1L, "Test", new BigDecimal("10"), null, "Test");
        
        // DÜZELTME: Mockito'ya expenseService.addExpense çağrıldığında
        // ne döndüreceğini söylüyoruz. Bu, NullPointerException'ı önler.
        when(expenseService.addExpense(any(Expense.class))).thenReturn(expense);

        AddExpenseCommand addCommand = new AddExpenseCommand(expenseService, expense);

        // Komutu çalıştır
        commandInvoker.executeCommand(addCommand);
        verify(expenseService, times(1)).addExpense(expense);
        assertTrue(commandInvoker.canUndo());
        assertFalse(commandInvoker.canRedo());

        // Geri al
        commandInvoker.undo();
        // DÜZELTME: Undo işlemi, komutta kaydedilen ID'yi kullanarak deleteExpense'i çağırır.
        verify(expenseService, times(1)).deleteExpense(1L);
        assertTrue(commandInvoker.canRedo());
        assertFalse(commandInvoker.canUndo());

        // İleri al (Redo)
        commandInvoker.redo();
        verify(expenseService, times(2)).addExpense(expense); // addExpense'in ikinci kez çağrıldığını doğrula
        assertTrue(commandInvoker.canUndo());
        assertFalse(commandInvoker.canRedo());
    }
}
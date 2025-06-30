package com.smart_budget_tracker.bil482;

import com.smart_budget_tracker.bil482.entity.Expense;
import com.smart_budget_tracker.bil482.repository.ExpenseRepository;
import com.smart_budget_tracker.bil482.service.ExpenseService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Çağan Durgun
 */
class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseService expenseService;

    private Expense expense;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        expense = Expense.builder()
                .id(1L)
                .title("Test Expense")
                .amount(new BigDecimal("100.00"))
                .category("Food")
                .date(LocalDateTime.now())
                .build();
    }

    @Test
    void addExpense_shouldSaveExpense() {
        when(expenseRepository.save(ArgumentMatchers.any(Expense.class))).thenReturn(expense);

        Expense saved = expenseService.addExpense(expense);

        assertNotNull(saved);
        assertEquals(expense.getId(), saved.getId());
        verify(expenseRepository, times(1)).save(expense);
    }

    @Test
    void editExpense_shouldUpdateExistingExpense() {
        when(expenseRepository.existsById(expense.getId())).thenReturn(true);
        when(expenseRepository.save(expense)).thenReturn(expense);

        Expense updated = expenseService.editExpense(expense);

        assertNotNull(updated);
        assertEquals(expense.getId(), updated.getId());
        verify(expenseRepository, times(1)).save(expense);
    }

    @Test
    void editExpense_shouldThrowExceptionWhenExpenseNotFound() {
        when(expenseRepository.existsById(expense.getId())).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> expenseService.editExpense(expense));

        assertEquals("Expense not found", exception.getMessage());
        verify(expenseRepository, never()).save(expense);
    }

    @Test
    void deleteExpense_shouldDeleteWhenExists() {
        when(expenseRepository.existsById(expense.getId())).thenReturn(true);
        doNothing().when(expenseRepository).deleteById(expense.getId());

        assertDoesNotThrow(() -> expenseService.deleteExpense(expense.getId()));

        verify(expenseRepository, times(1)).deleteById(expense.getId());
    }

    @Test
    void deleteExpense_shouldThrowExceptionWhenNotFound() {
        when(expenseRepository.existsById(expense.getId())).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> expenseService.deleteExpense(expense.getId()));

        assertEquals("Expense not found", exception.getMessage());
        verify(expenseRepository, never()).deleteById(expense.getId());
    }
}

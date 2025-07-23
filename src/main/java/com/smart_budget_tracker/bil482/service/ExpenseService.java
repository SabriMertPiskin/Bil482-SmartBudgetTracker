package com.smart_budget_tracker.bil482.service;

import com.smart_budget_tracker.bil482.entity.Expense;
import com.smart_budget_tracker.bil482.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Çağan Durgun
 */
@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense addExpense(Expense expense) {
        // Basit validasyonlar yapılabilir burada
        return expenseRepository.save(expense);
    }

    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    public Expense editExpense(Expense expense) {
        // Düzenlenecek harcama öncelikle var mı kontrol edilmeli
        if (expense.getId() == null || !expenseRepository.existsById(expense.getId())) {
            throw new IllegalArgumentException("Expense not found");
        }
        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new IllegalArgumentException("Expense not found");
        }
        expenseRepository.deleteById(id);
    }
}

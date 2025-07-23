package com.smart_budget_tracker.bil482.service;

import com.smart_budget_tracker.bil482.usecase_2.observer.BudgetManager;
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
    private final BudgetManager budgetManager;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

   public ExpenseService(ExpenseRepository expenseRepository, BudgetManager budgetManager) {
        this.expenseRepository = expenseRepository;
        this.budgetManager = budgetManager;
    }

    public Expense addExpense(Expense expense) {
        Expense savedExpense = expenseRepository.save(expense);
        budgetManager.notifyObservers(savedExpense); // Observer'ları tetikle
        return savedExpense;
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

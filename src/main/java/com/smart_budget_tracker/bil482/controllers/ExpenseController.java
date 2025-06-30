package com.smart_budget_tracker.bil482.controllers;

import com.smart_budget_tracker.bil482.entity.Expense;
import com.smart_budget_tracker.bil482.service.ExpenseService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Çağan Durgun
 */
@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
        Expense created = expenseService.addExpense(expense);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> editExpense(@PathVariable Long id, @RequestBody Expense expense) {
        expense.setId(id); // Path’den gelen id ile body id senkronize
        Expense updated = expenseService.editExpense(expense);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}

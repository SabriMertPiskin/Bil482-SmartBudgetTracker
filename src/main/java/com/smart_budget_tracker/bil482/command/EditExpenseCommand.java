package com.smart_budget_tracker.bil482.command;

import com.smart_budget_tracker.bil482.entity.Expense;
import com.smart_budget_tracker.bil482.service.ExpenseService;

public class EditExpenseCommand implements ExpenseCommand {
    private final ExpenseService expenseService;
    private final Expense newExpense;
    private Expense previousExpense;

    public EditExpenseCommand(ExpenseService expenseService, Expense newExpense) {
        this.expenseService = expenseService;
        this.newExpense = newExpense;
    }

    @Override
    public void execute() {
        // Önceki durumu kaydet
        this.previousExpense = expenseService.getExpenseById(newExpense.getId())
                .orElse(null);
        expenseService.editExpense(newExpense);
    }

    @Override
    public void undo() {
        if (previousExpense != null) {
            expenseService.editExpense(previousExpense);
        }
    }

    @Override
    public String getCommandType() {
        return "EDIT_EXPENSE";
    }
}
package com.smart_budget_tracker.bil482.command;

import com.smart_budget_tracker.bil482.entity.Expense;
import com.smart_budget_tracker.bil482.service.ExpenseService;

/**
 * @author Çağan Durgun
 */
public class DeleteExpenseCommand implements ExpenseCommand {
    private final ExpenseService expenseService;
    private final Long expenseId;
    private Expense deletedExpense;

    public DeleteExpenseCommand(ExpenseService expenseService, Long expenseId) {
        this.expenseService = expenseService;
        this.expenseId = expenseId;
    }

    @Override
    public void execute() {
        // Silmeden önce kaydet
        this.deletedExpense = expenseService.getExpenseById(expenseId)
                .orElse(null);
        expenseService.deleteExpense(expenseId);
    }

    @Override
    public void undo() {
        if (deletedExpense != null) {
            expenseService.addExpense(deletedExpense);
        }
    }

    @Override
    public String getCommandType() {
        return "DELETE_EXPENSE";
    }
}

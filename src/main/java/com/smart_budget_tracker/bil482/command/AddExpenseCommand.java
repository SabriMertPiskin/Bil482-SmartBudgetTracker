package com.smart_budget_tracker.bil482.command;

import com.smart_budget_tracker.bil482.entity.Expense;
import com.smart_budget_tracker.bil482.service.ExpenseService;

/**
 * @author Çağan Durgun
 */
public class AddExpenseCommand implements ExpenseCommand {
    private final ExpenseService expenseService;
    private final Expense expense;
    private Long createdExpenseId;

    public AddExpenseCommand(ExpenseService expenseService, Expense expense) {
        System.out.println("AddExpenseCommand 12");
        this.expenseService = expenseService;
        this.expense = expense;
    }

    @Override
    public void execute() {
        System.out.println("AddExpenseCommand 19");
        Expense created = expenseService.addExpense(expense);
        this.createdExpenseId = created.getId();
    }

    @Override
    public void undo() {
        System.out.println("AddExpenseCommand 26");
        if (createdExpenseId != null) {
            expenseService.deleteExpense(createdExpenseId);
        }
    }

    @Override
    public String getCommandType() {
        System.out.println("AddExpenseCommand 34");
        return "ADD_EXPENSE";
    }
}

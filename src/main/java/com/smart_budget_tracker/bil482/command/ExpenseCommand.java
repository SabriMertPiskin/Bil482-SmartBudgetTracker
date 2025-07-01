package com.smart_budget_tracker.bil482.command;

/**
 * @author Çağan Durgun
 */
public interface ExpenseCommand {

    void execute();

    void undo();

    String getCommandType();
}

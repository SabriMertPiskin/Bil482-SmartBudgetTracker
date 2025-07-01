package com.smart_budget_tracker.bil482.command;

import org.springframework.stereotype.Component;
import java.util.Stack;

@Component
public class ExpenseCommandInvoker {
    private final Stack<ExpenseCommand> commandHistory = new Stack<>();
    private final Stack<ExpenseCommand> undoHistory = new Stack<>();

    public void executeCommand(ExpenseCommand command) {
        command.execute();
        commandHistory.push(command);
        undoHistory.clear(); // Yeni komut çalıştırıldığında redo geçmişi temizlenir
    }

    public boolean undo() {
        if (!commandHistory.isEmpty()) {
            ExpenseCommand command = commandHistory.pop();
            command.undo();
            undoHistory.push(command);
            return true;
        }
        return false;
    }

    public boolean redo() {
        if (!undoHistory.isEmpty()) {
            ExpenseCommand command = undoHistory.pop();
            command.execute();
            commandHistory.push(command);
            return true;
        }
        return false;
    }

    public boolean canUndo() {
        return !commandHistory.isEmpty();
    }

    public boolean canRedo() {
        return !undoHistory.isEmpty();
    }
}
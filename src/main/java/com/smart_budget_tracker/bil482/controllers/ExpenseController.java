package com.smart_budget_tracker.bil482.controllers;

import com.smart_budget_tracker.bil482.entity.Expense;
import com.smart_budget_tracker.bil482.service.ExpenseService;
import com.smart_budget_tracker.bil482.command.*;
import com.smart_budget_tracker.bil482.factory.ExpenseFactory;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ExpenseCommandInvoker commandInvoker;
    private final ExpenseFactory expenseFactory;

    public ExpenseController(ExpenseService expenseService,
            ExpenseCommandInvoker commandInvoker,
            ExpenseFactory expenseFactory) {
        this.expenseService = expenseService;
        this.commandInvoker = commandInvoker;
        this.expenseFactory = expenseFactory;
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
        try {
            // Factory pattern kullanarak kategori bazlı expense oluştur
            Expense processedExpense = expenseFactory
                    .createExpenseBuilder(expense.getCategory())
                    .setTitle(expense.getTitle())
                    .setAmount(expense.getAmount())
                    .setDate(expense.getDate())
                    .setCategory(expense.getCategory())
                    .setDefaultCategory()
                    .setValidationRules()
                    .build();

            // Command pattern kullanarak ekle
            AddExpenseCommand command = new AddExpenseCommand(expenseService, processedExpense);
            commandInvoker.executeCommand(command);

            return ResponseEntity.ok(processedExpense);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/by-type")
    public ResponseEntity<Expense> addExpenseByType(@RequestBody Expense expense,
            @RequestParam String type) {
        try {
            // Tip bazlı factory kullanımı
            Expense processedExpense = expenseFactory
                    .createExpenseBuilderByType(type)
                    .setTitle(expense.getTitle())
                    .setAmount(expense.getAmount())
                    .setDate(expense.getDate())
                    .setCategory(expense.getCategory())
                    .setDefaultCategory()
                    .setValidationRules()
                    .build();

            AddExpenseCommand command = new AddExpenseCommand(expenseService, processedExpense);
            commandInvoker.executeCommand(command);

            return ResponseEntity.ok(processedExpense);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> editExpense(@PathVariable Long id, @RequestBody Expense expense) {
        try {
            expense.setId(id);

            // Factory pattern ile güncellenen expense'i işle
            Expense processedExpense = expenseFactory
                    .createExpenseBuilder(expense.getCategory())
                    .setTitle(expense.getTitle())
                    .setAmount(expense.getAmount())
                    .setDate(expense.getDate())
                    .setCategory(expense.getCategory())
                    .setDefaultCategory()
                    .setValidationRules()
                    .build();
            processedExpense.setId(id);

            // Command pattern kullanarak güncelle
            EditExpenseCommand command = new EditExpenseCommand(expenseService, processedExpense);
            commandInvoker.executeCommand(command);

            return ResponseEntity.ok(processedExpense);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        try {
            // Command pattern kullanarak sil
            DeleteExpenseCommand command = new DeleteExpenseCommand(expenseService, id);
            commandInvoker.executeCommand(command);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Undo/Redo endpoints
    @PostMapping("/undo")
    public ResponseEntity<Map<String, Object>> undoLastAction() {
        boolean success = commandInvoker.undo();
        return ResponseEntity.ok(Map.of(
                "success", success,
                "message", success ? "Son işlem geri alındı" : "Geri alınacak işlem yok"));
    }

    @PostMapping("/redo")
    public ResponseEntity<Map<String, Object>> redoLastAction() {
        boolean success = commandInvoker.redo();
        return ResponseEntity.ok(Map.of(
                "success", success,
                "message", success ? "İşlem yeniden yapıldı" : "Yeniden yapılacak işlem yok"));
    }

    @GetMapping("/can-undo")
    public ResponseEntity<Map<String, Boolean>> canUndo() {
        return ResponseEntity.ok(Map.of("canUndo", commandInvoker.canUndo()));
    }

    @GetMapping("/can-redo")
    public ResponseEntity<Map<String, Boolean>> canRedo() {
        return ResponseEntity.ok(Map.of("canRedo", commandInvoker.canRedo()));
    }

    // Yardımcı endpoint - Kategori tipini öğrenmek için
    @GetMapping("/category-type")
    public ResponseEntity<Map<String, String>> getCategoryType(@RequestParam String category) {
        String type = expenseFactory.determineExpenseType(category);
        return ResponseEntity.ok(Map.of(
                "category", category,
                "type", type));
    }
}
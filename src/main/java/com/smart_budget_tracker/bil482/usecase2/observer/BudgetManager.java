/**package com.smart_budget_tracker.bil482.usecase_2.observer;

import com.smart_budget_tracker.bil482.entity.Expense;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BudgetManager implements BudgetSubject {
    private final List<BudgetObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(BudgetObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(BudgetObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Expense expense) {
        for (BudgetObserver observer : observers) {
            observer.update(expense);
        }
    }
}*/

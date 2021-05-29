package com.javi.Libro1.domain;

import com.javi.Libro1.utils.InvalidUserException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    String name;
    String lastName;
    String email;
    String password;
    List<Category> customCategories = new ArrayList<>();
    List<Movement> transactions = new ArrayList<>();

    public User(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public void addTransaction(Movement newTransaction) {
        if (!customCategories.contains(newTransaction.getCategory()))
            throw new RuntimeException("Given category is not defined");

        transactions.add(newTransaction);
    }

    public List<Movement> getExpenses() {
        return transactions.stream().filter(Movement::isExpense).toList();
    }

    public List<Movement> getEarnigs() {
        return transactions.stream().filter(elem -> !elem.isExpense()).toList();
    }

    public Map<Category, Double> getExpensesByCategory() {
        return this.getListOfMovementsMappedByCategory(this.getExpenses());
    }

    public Map<Category, Double> getEarningsByCategory() {
        return this.getListOfMovementsMappedByCategory(this.getEarnigs());
    }

    public double getTotalBalance() {
        return this.transactions.stream().reduce(0.0, (total, elem) -> total + elem.getAmount(), Double::sum);
    }

    public Map<Category, Double> getListOfMovementsMappedByCategory(List<Movement> movements) {
        Map<Category, Double> expensesByCategory = new HashMap<>();
        this.customCategories.forEach(elem -> {
                    Double movementSum = movements
                            .stream()
                            .filter(e -> e.category == elem).toList()
                            .stream()
                            .reduce(0.0, (total, movement) -> total + movement.getAmount(), Double::sum);
                    expensesByCategory.put(elem, movementSum);
                }
        );
        return expensesByCategory;
    }

    public List<Category> getCustomCategories() {
        return customCategories;
    }

    public void addCustomCategory(Category newCategory) {
        this.customCategories.add(newCategory);
    }

    public void removeCustomCategory(Category deletedCategory) {
        this.customCategories.remove(deletedCategory);
    }


    public void validate() throws InvalidUserException {
        ArrayList<String> errors = new ArrayList<>();
        if (name.isBlank())
            errors.add("No name given");
        if (lastName.isBlank())
            errors.add("No name given");
        if (password.isBlank())
            errors.add("No name given");
        if (email.isBlank())
            errors.add("No name given");
        if (!errors.isEmpty())
            throw new InvalidUserException(errors);
    }

    public List<Movement> getTransactions() {
        return transactions;
    }
}

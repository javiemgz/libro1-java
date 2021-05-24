package com.javi.Libro1.domain;

import com.javi.Libro1.utils.InvalidUserException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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

    public void addTransaction(@NotNull Movement newTransaction) {
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

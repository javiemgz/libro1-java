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
    List<Category> customCategories = new ArrayList<Category>();
    List<Movement> transactions = new ArrayList<Movement>();

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

    public void validate() throws InvalidUserException {
        ArrayList<String> errors = new ArrayList<String>();
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

}

package com.javi.Libro1.domain;

import com.javi.Libro1.utils.InvalidUserException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class User {
    @Id @GeneratedValue
    Long id;
    String name;
    String lastName;
    @Column(unique = true)
    String email;
    String password;
    @Transient
    List<Category> customCategories = new ArrayList<>();
    @Transient
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
            errors.add("No lastname given");
        if (password.isBlank())
            errors.add("No password given");
        if (email.isBlank())
            errors.add("No email given");
        if (!errors.isEmpty())
            throw new InvalidUserException(errors);
    }

    public List<Movement> getTransactions() {
        return transactions;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

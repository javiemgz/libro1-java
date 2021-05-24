package com.javi.Libro1.domain;

import java.time.LocalDate;

class Movement {
    double amount;
    LocalDate date;
    String name;
    Category category;

    Movement(String name, double amount, Category category) {
        this.amount = amount;
        this.name = name;
        this.date = LocalDate.now();
        this.category = category;
    }

    public boolean isExpense() {
        return amount < 0;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

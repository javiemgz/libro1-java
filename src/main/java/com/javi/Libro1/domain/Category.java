package com.javi.Libro1.domain;

public class Category {
    String name;
    String color;

    Category(String name, String color) {
        this.name = name;
        this.color = color;
    }


    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}

package com.javi.Libro1.domain;

public class Category {
    long id;
    String name;
    String color;

    public Category(String name, String color, long id) {
        this.name = name;
        this.color = color;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Category comparedCategory) {

            return super.equals(comparedCategory) || this.id == comparedCategory.id;

        } else {
            return false;
        }
    }
}

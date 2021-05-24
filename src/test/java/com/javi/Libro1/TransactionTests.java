package com.javi.Libro1;

import com.javi.Libro1.domain.Category;
import com.javi.Libro1.domain.Movement;
import com.javi.Libro1.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionTests {

    private final User user = new User("Javi", "Gomez", "javi@gmail.com", "1234");
    private final Category addedCategory = new Category("Categoria de test", "#fffff");
    private final Category nonAddedCategory = new Category("Categoria de test", "#fffff");

    private final Movement expense = new Movement("Gasto", -1500, addedCategory);
    private final Movement earning = new Movement("Ingreso", 15000, addedCategory);


    @BeforeEach
    public void addCategory() {
        user.addCustomCategory(addedCategory);
    }


    @Test
    public void onlyCanAddMovemtsWithKnownCategories() {

        Movement wrongExpense = new Movement("Gasto", -1500, nonAddedCategory);
        assertThrows(RuntimeException.class,
                () -> user.addTransaction(wrongExpense)
        );

    }

}

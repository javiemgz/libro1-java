package com.javi.Libro1;

import com.javi.Libro1.domain.Category;
import com.javi.Libro1.domain.Movement;
import com.javi.Libro1.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTests {

    private final User user = new User("Javi", "Gomez", "javi@gmail.com", "1234");
    private final Category addedCategory = new Category("Categoria de test", "#fffff",1);
    private final Category nonAddedCategory = new Category("Categoria de test2", "#fffff",2);

    private final Movement expense = new Movement("Gasto", -1500, addedCategory);
    private final Movement earning = new Movement("Ingreso", 15000, addedCategory);


    @BeforeEach
    public void addCategory() {
        user.addCustomCategory(addedCategory);
        user.addTransaction(expense);
        user.addTransaction(earning);
        user.addTransaction(expense);
        user.addTransaction(earning);
    }


    @Test
    public void onlyCanAddMovemtsWithKnownCategories() {

        Movement wrongExpense = new Movement("Gasto", -1500, nonAddedCategory);
        assertThrows(RuntimeException.class,
                () -> user.addTransaction(wrongExpense)
        );

    }

    @Test
    public void getExpensesOnlyReturnsOutgoingTransactions() {
        List<Movement> expenses = user.getExpenses();
        assertTrue(expenses.stream().allMatch((elem) -> elem.getAmount() < 0));
    }

    @Test
    public void balanceIsEqualsToEarningsMinusExpenses(){
        //in this scenario earnings are equals to 30k and expenses to 3k
        assertEquals(27000, user.getTotalBalance());
    }

    @Test
    public void totalExpensesByCategory(){
        Movement otherExpense = new Movement("Gasto", -1500, nonAddedCategory);
        user.addCustomCategory(nonAddedCategory);
        user.addTransaction(otherExpense);
        Map<Category, Double> total = user.getExpensesByCategory();
        assertEquals(-3000, total.get(addedCategory));
        assertEquals(-1500, total.get(nonAddedCategory));
    }

    @Test
    public void GroupAndSumByCategory(){
        user.addCustomCategory(nonAddedCategory);

        List <Movement> listOfMovements = new ArrayList<>();
        listOfMovements.add(new Movement("trans cat 1", 500, addedCategory));
        listOfMovements.add(new Movement("trans cat 1", -100, addedCategory));
        listOfMovements.add(new Movement("trans cat 2", 500, nonAddedCategory));

        Map<Category, Double> total = user.getListOfMovementsMappedByCategory(listOfMovements);

        assertEquals(400, total.get(addedCategory));
        assertEquals(500, total.get(nonAddedCategory));
    }
}

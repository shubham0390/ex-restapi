package com.mmt.shubh.rest.resources.expense;

import org.springframework.stereotype.Component;


/**
 * Created by Subham Tyagi
 * On 7/24/2015.
 * <p>
 * TODO: Add class comments
 */
@Component
public class ExpenseResourceImpl implements IExpenseResource {

    @Override
    public void createExpense() {
       
    }

    @Override
    public void updateExpense() {
    }

    @Override
    public void moveExpense(long expenseId, long newExpenseBookId) {
    }

    public void getAllExpenses() {
    }

    public void syncExpenses(long syncId) {
    }

    @Override
    public void getExpenseList(long expenseBookId) {
    }

    @Override
    public void syncExpenses(long expenseBookId, long syncId) {
    }

    @Override
    public void deleteExpense() {

    }
}

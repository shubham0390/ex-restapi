package com.mmt.shubh.rest.resources.expense;

import javax.ws.rs.core.Response;

/**
 * Created by Subham Tyagi
 * On 7/24/2015.
 * <p>
 * TODO: Add class comments
 */
public class ExpenseResourceImpl implements IExpenseResource {

    @Override
    public Response createExpense() {
        return null;
    }

    @Override
    public Response updateExpense() {
        return null;
    }

    @Override
    public Response moveExpense(long expenseId, long newExpenseBookId) {
        return null;
    }

    @Override
    public Response getAllExpenses() {
        return null;
    }

    @Override
    public Response syncExpenses(long syncId) {
        return null;
    }

    @Override
    public Response getExpenseList(long expenseBookId) {
        return null;
    }

    @Override
    public Response syncExpenses(long expenseBookId, long syncId) {
        return null;
    }
}

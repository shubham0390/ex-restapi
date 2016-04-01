package com.mmt.shubh.database.repository.expense;

import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;

/**
 * Created by subhamtyagi on 3/30/16.
 */
@Component(value = "expenseSQLRepository")
public class ExpenseSQLRepository implements IExpenseRepository {
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
}

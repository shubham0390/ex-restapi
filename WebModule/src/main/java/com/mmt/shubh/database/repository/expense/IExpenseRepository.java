package com.mmt.shubh.database.repository.expense;

import javax.ws.rs.core.Response;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
public interface IExpenseRepository {

    Response createExpense();

    Response updateExpense();

    Response moveExpense(long expenseId, long newExpenseBookId);

    Response getAllExpenses();

    Response syncExpenses(long syncId);
}

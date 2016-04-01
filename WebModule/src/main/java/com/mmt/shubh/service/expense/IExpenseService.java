package com.mmt.shubh.service.expense;

import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
@Service
public interface IExpenseService {

    Response createExpense();

    Response updateExpense();

    Response moveExpense(long expenseId, long newExpenseBookId);

    Response getAllExpenses();

    Response syncExpenses(long syncId);
}

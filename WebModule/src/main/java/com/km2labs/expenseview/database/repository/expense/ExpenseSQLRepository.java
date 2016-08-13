package com.km2labs.expenseview.database.repository.expense;

import com.km2labs.expenseview.database.entity.ExpenseEntity;
import com.km2labs.expenseview.database.repository.BaseRepository;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;

/**
 * Created by subhamtyagi on 3/30/16.
 */
@Component(value = "expenseSQLRepository")
public class ExpenseSQLRepository extends BaseRepository<ExpenseEntity> implements IExpenseRepository {
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

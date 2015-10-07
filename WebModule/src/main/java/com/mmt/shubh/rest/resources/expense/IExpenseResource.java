package com.mmt.shubh.rest.resources.expense;

import com.mmt.shubh.rest.markers.ToJSON;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
public interface IExpenseResource {

    Response createExpense();

    Response updateExpense();

    Response moveExpense(long expenseId, long newExpenseBookId);

    Response getAllExpenses();

    Response syncExpenses(long syncId);
    @GET
    @Path("/expense/{expenseBookId}")
    @ToJSON
    Response getExpenseList(@PathParam("expenseBookId") long expenseBookId);

    @GET
    @Path("expenses/sync/{syncId}/{expenseBookId}")
    @ToJSON
    Response syncExpenses(@PathParam("expenseBookId") long expenseBookId, @PathParam("syncId") long syncId);
}

package com.mmt.shubh.rest.resources.expense;

import com.mmt.shubh.rest.markers.ToJSON;
import com.mmt.shubh.rest.model.Expense;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import java.util.List;


/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
@Service
@Path("/expense")
public interface IExpenseResource {

    @POST
    void createExpense();

    @PUT
    void updateExpense();

    @PUT
    void moveExpense(long expenseId, long newExpenseBookId);

    @GET
    @Path("/{expenseBookId}")
    @ToJSON
    void getExpenseList(@PathParam("expenseBookId") long expenseBookId);

    @GET
    @Path("/sync/{syncId}/{expenseBookId}")
    @ToJSON
    void syncExpenses(@PathParam("expenseBookId") long expenseBookId, @PathParam("syncId") long syncId);

    @DELETE
    @Path("/")
    void deleteExpense();
}

package com.km2labs.expenseview.rest.resources.expense;

import com.km2labs.expenseview.rest.markers.ToJSON;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;


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

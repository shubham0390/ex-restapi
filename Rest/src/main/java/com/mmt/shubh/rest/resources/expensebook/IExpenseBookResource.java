package com.mmt.shubh.rest.resources.expensebook;

import com.mmt.shubh.rest.markers.FromJSON;
import com.mmt.shubh.rest.markers.ToJSON;
import com.mmt.shubh.rest.model.ExpenseBook;
import com.mmt.shubh.rest.model.Member;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/9/2015.
 * <p>
 * TODO: Add class comments
 */
@Path("/expensebook")
@Service
public interface IExpenseBookResource {

    @POST
    @FromJSON
    Response createExpenseBook(ExpenseBook expenseBook);

    @PUT
    @FromJSON
    Response updateExpenseBook(ExpenseBook expenseBook);

    @POST
    @Path("/member/{expenseBookId}")
    Response addMember(Member member, @PathParam("expenseBookId") long expenseBookId);

    @POST
    @Path("/member")
    @FromJSON
    Response addMembers(List<Member> memberList, @PathParam("expenseBookId") long expenseBookId);


    @GET
    @Path("/{expenseBookId}")
    @ToJSON
    Response getExpenseBookDetails(@PathParam("expenseBookId") long expenseBookId);

    @GET
    @ToJSON
    Response getExpenseBookList(String memberEmailId);

    @GET
    @ToJSON
    Response getExpenseBookList(long memberId);


    @DELETE
    @Path("/member")
    Response deleteMember(@QueryParam("expenseBookId") long expenseBookId);

    @DELETE
    @Path("/{expenseBookId}")
    Response deleteExpenseBook(@PathParam("expenseBookId") long expenseBookId);

}

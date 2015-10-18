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
@Produces("application/json")
@Consumes("application/json")
public interface IExpenseBookResource {

    @POST
    void createExpenseBook(ExpenseBook expenseBook);

    @PUT
    void updateExpenseBook(ExpenseBook expenseBook);

    @GET
    ExpenseBook getExpenseBookDetails(@QueryParam("expenseBookId") String clientId);

    @GET
    List<ExpenseBook> getExpenseBookList(String memberEmailId);

    @GET
    List<ExpenseBook> getExpenseBookList(long memberId);


    @DELETE
    void deleteExpenseBook(@QueryParam("expenseBookId") String clientId);


    @POST
    @Path("/member")
    void addMember(Member member, @QueryParam("expenseBookId") String clientId);

    @POST
    @Path("/member")
    void addMembers(List<Member> memberList, @QueryParam("expenseBookId") String clientId);

    @DELETE
    @Path("/member")
    void deleteMember(@QueryParam("expenseBookId") String clientId, @QueryParam("memberEmailId") String memberEmailId);
}

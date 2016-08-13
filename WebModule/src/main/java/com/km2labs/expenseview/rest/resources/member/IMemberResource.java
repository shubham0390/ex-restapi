package com.km2labs.expenseview.rest.resources.member;

import com.km2labs.expenseview.rest.model.Member;
import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
@Path("/members")
@Produces("application/json")
@Consumes("application/json")
@Service
public interface IMemberResource {

    @PUT
    Member updateMember(Member member);

    @GET
    @Valid
    Member getMember(@Email @QueryParam("emailId") String emailId);

    @GET
    @Path("/espensebook")
    @Valid
    List<Member> getExpenseBookMembers(@NotNull @QueryParam("expenseBookId") long expenseBookId);


    @DELETE
    @Valid
    String deleteMember(@NotNull @Email @QueryParam("emailId") String emailId);


    @GET
    @Path("member/sync/{syncId}/{expenseBookId}")
    Response syncMember(@PathParam("expenseBookId") long expenseBookId, @PathParam("syncId") long syncId);


    @GET
    @Path("/demo")
    @Produces("text/plain")
    String getDemo();


}

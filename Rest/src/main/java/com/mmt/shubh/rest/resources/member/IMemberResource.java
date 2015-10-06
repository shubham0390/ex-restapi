package com.mmt.shubh.rest.resources.member;

import com.mmt.shubh.rest.markers.ToJSON;
import com.mmt.shubh.rest.model.DeviceDetails;
import com.mmt.shubh.rest.model.Member;
import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
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

    @POST
    @Path("/register")
    Response registerMember(Member member);

    @PUT
    Response updateMember(Member member);

    @PUT
    @Produces("application/json")
    @Path("/device")
    @Valid
    Response updateGCMToken(@NotNull @PathParam("GCMToekn") String GCMToken,
                            @NotNull @Email @PathParam("emailId") String emailId);

    @GET
    @Valid
    Member getMember(@Email @QueryParam("emailId") String emailId);

    @GET
    @Path("/members/{expenseBookId}")
    @ToJSON
    Response getMembers(@PathParam("expenseBookId") long expenseBookId);

    @PUT
    @Path("/device/{emailId}")
    @Valid
    Response updateDevice(@NotNull @Email @PathParam("emailId") String emailId, DeviceDetails deviceDetails);

    @DELETE
    @Path("/device/{deviceUUID}/{emailId}")
    @Valid
    Response deleteDevice(@NotNull @PathParam("deviceUUID") String detailsUUID,
                          @NotNull @Email @PathParam("emailId") String emailId);

    @DELETE
    @Path("/{emailId}")
    @Valid
    Response deleteMember(@NotNull @Email @PathParam("emailId") String emailId);

    @GET
    @Path("/espensebook")
    @Valid
    List<Member> getExpenseBookMembers(@NotNull @QueryParam("expenseBookId") long expenseBookId);


    @GET
    @Path("member/sync/{syncId}/{expenseBookId}")
    @ToJSON
    Response syncMember(@PathParam("expenseBookId") long expenseBookId, @PathParam("syncId") long syncId);


    @GET
    @Path("/demo")
    @Produces("text/plain")
    String getDemo();
}

package com.mmt.shubh.rest.resources.member;

import com.mmt.shubh.rest.model.DeviceDetails;
import com.mmt.shubh.rest.model.Member;
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

    @POST
    Member registerMember(Member member);

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

    @PUT
    @Produces("application/json")
    @Path("/device/{GCMToken}")
    @Valid
    String updateGCMToken(@NotNull @PathParam("GCMToken") String GCMToken,
                          @NotNull @Email @QueryParam("emailId") String emailId);

    @POST
    @Path("/device")
    @Valid
    long addDevice(@NotNull @QueryParam("memberId") long memberId, DeviceDetails deviceDetails);

    @PUT
    @Path("/device")
    @Valid
    DeviceDetails updateDevice(@NotNull @Email @QueryParam("emailId") String emailId, DeviceDetails deviceDetails);

    @DELETE
    @Path("/device")
    @Valid
    String deleteDevice(@NotNull @QueryParam("deviceUUID") String detailsUUID,
                        @NotNull @Email @QueryParam("emailId") String emailId);

    @GET
    @Path("/device")
    List<DeviceDetails> getMemberDevices(@NotNull @QueryParam("memberId") long memberId);

    @GET
    @Path("member/sync/{syncId}/{expenseBookId}")
    Response syncMember(@PathParam("expenseBookId") long expenseBookId, @PathParam("syncId") long syncId);


    @GET
    @Path("/demo")
    @Produces("text/plain")
    String getDemo();
}

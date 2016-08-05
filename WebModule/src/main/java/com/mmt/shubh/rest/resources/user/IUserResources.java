package com.mmt.shubh.rest.resources.user;

import com.mmt.shubh.rest.ApiResult;
import com.mmt.shubh.rest.model.Device;
import com.mmt.shubh.rest.model.User;
import com.mmt.shubh.rest.model.result.MemberRegistrationResult;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;


@Path("/user")
@Service
@Produces("application/json")
@Consumes("application/json")
public interface IUserResources {

    @POST
    ApiResult<MemberRegistrationResult> signup(User user);

    @PUT
    @Path("/login/{mobileno}")
    ApiResult<MemberRegistrationResult> login(@PathParam("mobileno") String phoneNumber);

    @PUT
    @Path("/logout")
    void logout(String emailId);

    ApiResult<MemberRegistrationResult> generateOTP(long memberId);

    @POST
    @Path("/{userId}/devices")
    @Valid
    long addDevice(@NotNull @PathParam("userId") long memberId, Device device);

    @PUT
    @Path("/{userId}/devices")
    @Valid
    Device updateDevice(@NotNull @PathParam("userId") String emailId, Device device);

    @DELETE
    @Path("/{userId}/devices")
    @Valid
    String deleteDevice(@NotNull @QueryParam("deviceUUID") String detailsUUID,
                        @NotNull @PathParam("userId") String emailId);

    @GET
    @Path("/{userId}/devices")
    User getDevices(@NotNull @PathParam("userId") String memberId);



}

package com.km2labs.expenseview.rest.resources.user;

import com.km2labs.expenseview.rest.model.Device;
import com.km2labs.expenseview.rest.model.User;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/user")
@Service
@Produces("application/json")
@Consumes("application/json")
public interface IUserResources {

    @POST
    Response signup(SignupRequest signupRequest);

    @PUT
    @Path("/login")
    Response login(LoginRequest loginRequest);

    @PUT
    @Path("/logout")
    void logout(String emailId);

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

    @GET
    @Path("/demo")
    LoginRequest getDemo();


}

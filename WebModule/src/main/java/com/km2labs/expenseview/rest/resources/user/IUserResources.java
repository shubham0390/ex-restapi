/*
 * Copyright (c) 2016. . The Km2Labs Project
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */

package com.km2labs.expenseview.rest.resources.user;

import com.km2labs.expenseview.rest.dto.Device;
import com.km2labs.expenseview.rest.dto.auth.LoginRequestDto;
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
    @Path("/{userId}/devices")
    @Valid
    long addDevice(@NotNull @PathParam("userId") long userID, Device device);

    @PUT
    @Path("/{userId}/devices/{deviceId}")
    @Valid
    Response updateGCMTokenDevice(@NotNull @PathParam("userId") String userId, @PathParam("deviceId") String deviceId, String GCMToken);

    @DELETE
    @Path("/{userId}/devices")
    @Valid
    Response deleteDevice(@NotNull @QueryParam("deviceId") String deviceId,
                          @NotNull @PathParam("userId") String userId);

    @GET
    @Path("/{userId}/devices")
    Response getDevices(@NotNull @PathParam("userId") String userId);

    @GET
    @Path("/demo")
    LoginRequestDto getDemo();


}

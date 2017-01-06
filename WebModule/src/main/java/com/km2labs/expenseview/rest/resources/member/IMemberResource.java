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

package com.km2labs.expenseview.rest.resources.member;

import com.km2labs.expenseview.rest.dto.Member;
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

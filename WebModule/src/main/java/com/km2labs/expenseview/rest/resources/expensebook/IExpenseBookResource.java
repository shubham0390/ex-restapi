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

package com.km2labs.expenseview.rest.resources.expensebook;

import com.km2labs.expenseview.rest.model.ExpenseBook;
import com.km2labs.expenseview.rest.model.Member;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
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
    List<ExpenseBook> getExpenseBookList(@QueryParam("memberEmailId") String memberEmailId);

    @GET
    List<ExpenseBook> getExpenseBookList(@PathParam("memberId") long memberId);


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

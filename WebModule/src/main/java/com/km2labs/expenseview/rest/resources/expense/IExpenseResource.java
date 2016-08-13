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

package com.km2labs.expenseview.rest.resources.expense;

import com.km2labs.expenseview.rest.markers.ToJSON;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;


/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
@Service
@Path("/expense")
public interface IExpenseResource {

    @POST
    void createExpense();

    @PUT
    void updateExpense();

    @PUT
    void moveExpense(long expenseId, long newExpenseBookId);

    @GET
    @Path("/{expenseBookId}")
    @ToJSON
    void getExpenseList(@PathParam("expenseBookId") long expenseBookId);

    @GET
    @Path("/sync/{syncId}/{expenseBookId}")
    @ToJSON
    void syncExpenses(@PathParam("expenseBookId") long expenseBookId, @PathParam("syncId") long syncId);

    @DELETE
    @Path("/")
    void deleteExpense();
}

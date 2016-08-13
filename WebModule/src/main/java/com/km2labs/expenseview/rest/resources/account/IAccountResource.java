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

package com.km2labs.expenseview.rest.resources.account;

import com.km2labs.expenseview.rest.model.Account;
import com.km2labs.expenseview.rest.model.AccountTransaction;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by subhamtyagi on 2/18/16.
 */
@Service
@Path("/account")
public interface IAccountResource {

    @POST
    void addAccount(Account account);

    @DELETE
    @Path("/{accountId}")
    void deleteAccount(@NotNull @PathParam("accountId") long accountId, @NotNull @QueryParam("memberEmail") String memberEmailId);

    @PUT
    long updateAccount(Account account);

    @GET
    @Path("/{memberEmail}")
    List<Account> getAllForMember(@NotNull @PathParam("memberEmail") String memberEmailId);

    @POST
    @Path("/transaction")
    void addTransaction(AccountTransaction transaction);

    @PUT
    @Path("/transaction")
    void updateTransaction(AccountTransaction transaction);

    @DELETE
    @Path("/transaction")
    void deleteTransaction(long transactionId);

    @GET
    @Path("/transaction/{accountId}")
    List<AccountTransaction> getAllAccountTransaction(@NotNull @PathParam("accountId") long accountId);
}

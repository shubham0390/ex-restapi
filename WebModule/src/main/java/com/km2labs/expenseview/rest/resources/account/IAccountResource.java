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

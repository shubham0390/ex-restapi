package com.km2labs.expenseview.service.account;

import com.km2labs.expenseview.rest.model.Account;
import com.km2labs.expenseview.rest.model.AccountTransaction;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by subhamtyagi on 2/18/16.
 */
@Service
public interface IAccountService {

    void addAccount(Account account);

    void deleteAccount(long accountId,String memberEmailId);

    long updateAccount(Account account);

    List<Account> getAllForMember(String memberEmailId);

    void addTransaction(AccountTransaction transaction);

    void updateTransaction(AccountTransaction transaction);

    void deleteTransaction(long transactionId);

    List<AccountTransaction> getAllAccountTransaction(long accountId);

}

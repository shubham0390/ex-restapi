package com.mmt.shubh.service.account;

import com.mmt.shubh.rest.model.Account;
import com.mmt.shubh.rest.model.AccountTransaction;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by subhamtyagi on 2/18/16.
 */
@Service
public interface IAccountService {

    long addAccount(Account account);

    void deleteAccount(long accountId,String memberEmailId);

    long updateAccount(Account account);

    List<Account> getAllForMember(String memberEmailId);

    void addTransaction(AccountTransaction transaction);

    void updateTransaction(AccountTransaction transaction);

    void deleteTransaction(long transactionId);

    List<AccountTransaction> getAllAccountTransaction(long accountId);

}

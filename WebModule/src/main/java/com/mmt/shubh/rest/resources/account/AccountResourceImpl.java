package com.mmt.shubh.rest.resources.account;

import com.mmt.shubh.rest.model.Account;
import com.mmt.shubh.rest.model.AccountTransaction;
import com.mmt.shubh.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by subhamtyagi on 2/18/16.
 */
@Component
public class AccountResourceImpl implements IAccountResource {

    @Autowired
    @Qualifier(value = "accountServiceImpl")
    IAccountService mAccountService;

    @Override
    public void addAccount(Account account) {
        mAccountService.addAccount(account);
    }

    @Override
    public void deleteAccount(long accountId, String memberEmailId) {

    }

    @Override
    public long updateAccount(Account account) {
        return 0;
    }

    @Override
    public List<Account> getAllForMember(String memberEmailId) {
        return null;
    }

    @Override
    public void addTransaction(AccountTransaction transaction) {

    }

    @Override
    public void updateTransaction(AccountTransaction transaction) {

    }

    @Override
    public void deleteTransaction(long transactionId) {

    }

    @Override
    public List<AccountTransaction> getAllAccountTransaction(long accountId) {
        return null;
    }
}

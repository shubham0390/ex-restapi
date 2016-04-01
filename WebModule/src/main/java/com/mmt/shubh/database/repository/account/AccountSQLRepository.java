package com.mmt.shubh.database.repository.account;

import com.mmt.shubh.database.entity.AccountEntity;
import com.mmt.shubh.database.entity.AccountTransactionEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by subhamtyagi on 2/18/16.
 */

@Component(value = "accountSQLRepository")
public class AccountSQLRepository implements IAccountRepository {

    @Override
    public long addAccount(AccountEntity accountEntity) {
        return 0;
    }

    @Override
    public void deleteAccount(long accountId, String memberEmailId) {

    }

    @Override
    public long updateAccount(AccountEntity account) {
        return 0;
    }

    @Override
    public List<AccountEntity> getAllForMember(String memberEmailId) {
        return null;
    }

    @Override
    public void addTransaction(AccountTransactionEntity transaction) {

    }

    @Override
    public void updateTransaction(AccountTransactionEntity transaction) {

    }

    @Override
    public void deleteTransaction(long transactionId) {

    }

    @Override
    public List<AccountTransactionEntity> getAllAccountTransaction(long accountId) {
        return null;
    }

}

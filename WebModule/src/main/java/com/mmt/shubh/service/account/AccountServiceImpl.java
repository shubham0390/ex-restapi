package com.mmt.shubh.service.account;

import com.mmt.shubh.database.entity.AccountEntity;
import com.mmt.shubh.database.repository.account.IAccountRepository;
import com.mmt.shubh.rest.model.Account;
import com.mmt.shubh.rest.model.AccountTransaction;
import com.mmt.shubh.service.converter.IEntityModelConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by subhamtyagi on 2/18/16.
 */
@Component(value = "accountServiceImpl")
@Slf4j
public class AccountServiceImpl implements IAccountService {

    @Autowired
    @Qualifier(value = "accountSQLRepository")
    private IAccountRepository mAccountRepository;

    private IEntityModelConverter<AccountEntity,Account> mAccountIEntityModelConverter;

    @Override
    public long addAccount(String memberEmailId, Account account) {
        return mAccountRepository.addAccount(memberEmailId,mAccountIEntityModelConverter.toEntity(account));
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

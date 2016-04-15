package com.mmt.shubh.service.converter;

import com.mmt.shubh.database.entity.AccountEntity;
import com.mmt.shubh.rest.model.Account;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component(value = "accountEntityModelConverter")
public class AccountEntityModelConverter implements IEntityModelConverter<AccountEntity, Account> {

    @Override
    public AccountEntity toEntity(Account account) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountType(account.getAccountType());
        accountEntity.setAccountName(account.getAccountName());
        accountEntity.setAccountBalance(account.getAccountBalance());
        return accountEntity;
    }

    @Override
    public Account toModel(AccountEntity accountEntity) {
        Account account = new Account();
        account.setAccountBalance(accountEntity.getAccountBalance());
        account.setAccountName(accountEntity.getAccountName());
        account.setMember(accountEntity.getMember().getMemberEmail());
        account.setAccountType(accountEntity.getAccountType());
        return account;
    }

    @Override
    public List<AccountEntity> toEntity(List<Account> m) {
        return m.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<Account> toModel(List<AccountEntity> e) {
        return e.stream().map(this::toModel).collect(Collectors.toList());
    }
}

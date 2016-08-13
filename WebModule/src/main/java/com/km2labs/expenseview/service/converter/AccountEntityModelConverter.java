package com.km2labs.expenseview.service.converter;

import com.km2labs.expenseview.database.entity.AccountEntity;
import com.km2labs.expenseview.rest.model.Account;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;


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
    public Collection<AccountEntity> toEntity(Collection<Account> m) {
        return m.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public Collection<Account> toModel(Collection<AccountEntity> e) {
        return e.stream().map(this::toModel).collect(Collectors.toList());
    }
}

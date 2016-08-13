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

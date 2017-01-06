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

package com.km2labs.expenseview.service.account;

import com.km2labs.expenseview.database.entity.AccountEntity;
import com.km2labs.expenseview.database.entity.MemberEntity;
import com.km2labs.expenseview.database.repository.account.IAccountRepository;
import com.km2labs.expenseview.rest.model.Account;
import com.km2labs.expenseview.rest.model.AccountTransaction;
import com.km2labs.expenseview.rest.dto.Member;
import com.km2labs.expenseview.service.converter.IEntityModelConverter;
import com.km2labs.expenseview.service.member.IMemberService;
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

    @Autowired
    @Qualifier(value = "memberServiceImpl")
    private IMemberService mMemberService;

    @Autowired
    @Qualifier(value = "accountEntityModelConverter")
    private IEntityModelConverter<AccountEntity, Account> mAccountIEntityModelConverter;

    @Qualifier(value = "memberEntityModelConverter")
    @Autowired
    IEntityModelConverter<MemberEntity, Member> mEntityModelConverter;

    @Override
    public void addAccount(Account account) {
        AccountEntity accountEntity = mAccountIEntityModelConverter.toEntity(account);
        Member member = mMemberService.getMemberByEmail(account.getMember());
        accountEntity.setMember(mEntityModelConverter.toEntity(member));
        mAccountRepository.addAccount(accountEntity);
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

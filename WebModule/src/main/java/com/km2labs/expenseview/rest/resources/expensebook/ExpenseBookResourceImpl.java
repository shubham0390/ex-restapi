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

package com.km2labs.expenseview.rest.resources.expensebook;

import com.km2labs.expenseview.rest.model.ExpenseBook;
import com.km2labs.expenseview.rest.model.Member;
import com.km2labs.expenseview.service.expensebook.IExpenseBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/9/2015.
 * <p>
 * TODO: Add class comments
 */
@Component
@Slf4j
public class ExpenseBookResourceImpl implements IExpenseBookResource {

    @Autowired
    @Qualifier(value = "expenseBookServiceImpl")
    private IExpenseBookService mExpenseBookService;

    @Override
    public void createExpenseBook(ExpenseBook expenseBook) {
        mExpenseBookService.createExpenseBook(expenseBook);
    }

    @Override
    public void updateExpenseBook(ExpenseBook expenseBook) {
        mExpenseBookService.updateExpenseBook(expenseBook);
    }

    @Override
    public void addMember(Member member, String clientId) {
        mExpenseBookService.addMember(clientId, member);
    }

    @Override
    public void addMembers(List<Member> memberList, String clientId) {
        mExpenseBookService.addMembers(memberList, clientId);
    }


    @Override
    public ExpenseBook getExpenseBookDetails(String id) {
        return mExpenseBookService.getExpenseBookDetails(id);
    }

    @Override
    public List<ExpenseBook> getExpenseBookList(String memberEmailId) {
        return mExpenseBookService.getExpenseBookList(memberEmailId);
    }

    @Override
    public List<ExpenseBook> getExpenseBookList(long memberId) {
        return mExpenseBookService.getExpenseBookList(memberId);
    }

    @Override
    public void deleteMember(String id, String memberEmailId) {
        mExpenseBookService.deleteMember(id,memberEmailId);
    }

    @Override
    public void deleteExpenseBook(String id) {
        mExpenseBookService.deleteExpenseBook(id);
    }

}

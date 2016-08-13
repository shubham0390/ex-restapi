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

package com.km2labs.expenseview.service.expensebook;

import com.km2labs.expenseview.database.entity.ExpenseBookEntity;
import com.km2labs.expenseview.rest.model.ExpenseBook;
import com.km2labs.expenseview.rest.model.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/9/2015.
 * <p>
 * TODO: Add class comments
 */
@Service
public interface IExpenseBookService {

    void createExpenseBook(ExpenseBook expenseBook);

    void updateExpenseBook(ExpenseBook expenseBook);

    @Transactional
    void addMember(String clientId, Member member);

    void addMember(ExpenseBookEntity clientId, Member member);

    ExpenseBook getExpenseBookDetails(String clientId);

    List<ExpenseBook> getExpenseBookList(String memberEmailId);

    void deleteMember(String clientId, String memberEmailId);

    void deleteExpenseBook(String clientId);

    void addMembers(List<Member> memberList, String clientId);

    void addMembers(List<Member> memberList, ExpenseBookEntity clientId);

    List<ExpenseBook> getExpenseBookList(long memberId);

}

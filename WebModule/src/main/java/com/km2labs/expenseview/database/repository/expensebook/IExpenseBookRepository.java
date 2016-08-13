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

package com.km2labs.expenseview.database.repository.expensebook;


import com.km2labs.expenseview.database.entity.ExpenseBookEntity;
import com.km2labs.expenseview.database.entity.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/9/2015.
 * <p>
 * TODO: Add class comments
 */
@Repository
public interface IExpenseBookRepository {

    void createExpenseBook(ExpenseBookEntity expenseBookEntity);

    void updateExpenseBook(ExpenseBookEntity expenseBookEntity);

    void addMember(String clientId, MemberEntity memberEntity);

    void addMember(ExpenseBookEntity expenseBookEntity, MemberEntity memberEntity);

    ExpenseBookEntity getExpenseBookDetails(String clientId);

    void deleteMember(String clientId);

    void deleteExpenseBook(String clientId);

    ExpenseBookEntity getExpenseBookByMember(String clientId, String memberEmail);

    List<ExpenseBookEntity> getExpenseBook(String memberEmailId);

    List<ExpenseBookEntity> getExpenseBook(long memberId);
}

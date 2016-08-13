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

package com.km2labs.expenseview.database.repository.expense;

import com.km2labs.expenseview.database.entity.ExpenseEntity;
import com.km2labs.expenseview.database.repository.BaseRepository;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;

/**
 * Created by subhamtyagi on 3/30/16.
 */
@Component(value = "expenseSQLRepository")
public class ExpenseSQLRepository extends BaseRepository<ExpenseEntity> implements IExpenseRepository {
    @Override
    public Response createExpense() {
        return null;
    }

    @Override
    public Response updateExpense() {
        return null;
    }

    @Override
    public Response moveExpense(long expenseId, long newExpenseBookId) {
        return null;
    }

    @Override
    public Response getAllExpenses() {
        return null;
    }

    @Override
    public Response syncExpenses(long syncId) {
        return null;
    }
}

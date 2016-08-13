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
import com.km2labs.expenseview.database.entity.ExpenseBookEntity_;
import com.km2labs.expenseview.database.entity.MemberEntity;
import com.km2labs.expenseview.database.repository.BaseRepository;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Metamodel;
import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/9/2015.
 * <p>
 * TODO: Add class comments
 */
@Component(value = "expenseBookSQLRepository")
public class ExpenseBookSQLRepository extends BaseRepository<ExpenseBookEntity> implements IExpenseBookRepository {

    public ExpenseBookSQLRepository() {
        setClazz(ExpenseBookEntity.class);
    }

    @Override
    public void createExpenseBook(ExpenseBookEntity expenseBookEntity) {
        create(expenseBookEntity);
    }


    @Override
    public void updateExpenseBook(ExpenseBookEntity expenseBookEntity) {
        update(expenseBookEntity);
    }

    @Override
    public void addMember(String clientId, MemberEntity memberEntity) {
        ExpenseBookEntity expenseBookEntity = getExpenseBookDetails(clientId);
        expenseBookEntity.getMemberList().add(memberEntity);
        updateExpenseBook(expenseBookEntity);
    }

    @Override
    public void addMember(ExpenseBookEntity expenseBookEntity, MemberEntity memberEntity) {
        expenseBookEntity.getMemberList().add(memberEntity);
        updateExpenseBook(expenseBookEntity);
    }

    @Override
    public ExpenseBookEntity getExpenseBookDetails(String clientId) {
        return findByColumnNameAndValue(ExpenseBookEntity_.clientId, clientId);
    }

    @Override
    public void deleteMember(String clientId) {

    }

    @Override
    public void deleteExpenseBook(String clientId) {
        delete(getExpenseBookDetails(clientId));
    }

    @Override
    public ExpenseBookEntity getExpenseBookByMember(String clientId, String memberEmail) {
        CriteriaBuilder builder = mEntityManager.getCriteriaBuilder();
        CriteriaQuery<ExpenseBookEntity> query = builder.createQuery(ExpenseBookEntity.class);

        Metamodel metamodel = mEntityManager.getMetamodel();
        Root<ExpenseBookEntity> entityRoot = query.from(metamodel.entity(ExpenseBookEntity.class));
        query.select(entityRoot);
        query.where(
                builder.and(
                        builder.equal(
                                entityRoot.get(ExpenseBookEntity_.clientId), clientId
                        ),
                        builder.equal(
                                entityRoot.get(ExpenseBookEntity_.ownerEmailId), memberEmail

                        )
                )
        );
        return mEntityManager.createQuery(query).getSingleResult();
    }


    @Override
    public List<ExpenseBookEntity> getExpenseBook(String memberEmailId) {
        CriteriaBuilder builder = mEntityManager.getCriteriaBuilder();
        CriteriaQuery<ExpenseBookEntity> query = builder.createQuery(ExpenseBookEntity.class);

        Metamodel metamodel = mEntityManager.getMetamodel();
        Root<ExpenseBookEntity> entityRoot = query.from(metamodel.entity(ExpenseBookEntity.class));
        query.select(entityRoot);
        query.where(builder.equal(entityRoot.get(ExpenseBookEntity_.ownerEmailId), memberEmailId));
        return mEntityManager.createQuery(query).getResultList();
    }

    @Override
    //TODO : learn joins in JPA
    public List<ExpenseBookEntity> getExpenseBook(long memberId) {
        CriteriaBuilder builder = mEntityManager.getCriteriaBuilder();
        CriteriaQuery<ExpenseBookEntity> query = builder.createQuery(ExpenseBookEntity.class);
        Metamodel metamodel = mEntityManager.getMetamodel();
        String s = "SELECT * FROM expensebookentity where expensebookentity.id in(SELECT expensebook_id FROM expensebook_member " +
                "where member_id = 2)";

        Root<ExpenseBookEntity> entityRoot = query.from(metamodel.entity(ExpenseBookEntity.class));
        Join<ExpenseBookEntity, MemberEntity> join = entityRoot.join(ExpenseBookEntity_.memberList);

        query.select(entityRoot);
        query.where(builder.equal(entityRoot.get(ExpenseBookEntity_.memberList).in(memberId), memberId));
        return mEntityManager.createQuery(query).getResultList();
    }

}

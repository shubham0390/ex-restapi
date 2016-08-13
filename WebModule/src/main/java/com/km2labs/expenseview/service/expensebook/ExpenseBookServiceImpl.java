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
import com.km2labs.expenseview.database.entity.MemberEntity;
import com.km2labs.expenseview.database.repository.expensebook.IExpenseBookRepository;
import com.km2labs.expenseview.database.repository.member.IMemberRepository;
import com.km2labs.expenseview.rest.model.ExpenseBook;
import com.km2labs.expenseview.rest.model.Member;
import com.km2labs.expenseview.service.converter.IEntityModelConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/9/2015.
 * <p>
 * TODO: Add class comments
 */
@Component(value = "expenseBookServiceImpl")
@Slf4j
public class ExpenseBookServiceImpl implements IExpenseBookService {


    @Autowired
    @Qualifier(value = "expenseBookSQLRepository")
    IExpenseBookRepository mExpenseBookRepository;

    @Qualifier(value = "memberSQLRepository")
    @Autowired
    IMemberRepository mMemberRepository;

    @Qualifier(value = "expenseBookEntityModelConverter")
    @Autowired
    IEntityModelConverter<ExpenseBookEntity, ExpenseBook> mExpenseBookEntityModelConverter;

    @Qualifier(value = "memberEntityModelConverter")
    @Autowired
    IEntityModelConverter<MemberEntity, Member> mEntityModelConverter;

    @Transactional
    public void createExpenseBook(ExpenseBook expenseBook) {
        log.debug("START createExpenseBook");
        ExpenseBookEntity expenseBookEntity = null;

        expenseBookEntity = getExpenseBookEntity(expenseBook, expenseBookEntity);

        if (expenseBookEntity == null) {
            List<MemberEntity> entities = createMembers(expenseBook.getMemberList());

            expenseBookEntity = mExpenseBookEntityModelConverter.toEntity(expenseBook);
            mExpenseBookRepository.createExpenseBook(expenseBookEntity);
            expenseBookEntity.setMemberList(new HashSet<>(entities));
            addM(expenseBookEntity);
        } else {
           /* throw new WebApplicationException("Expense book already present with following name "
                    + expenseBook.getName() + "for following member" + expenseBook.getOwnerEmailId());*/
        }


        log.debug("ENDS createExpenseBook");
    }

    @Transactional
    private void addM(ExpenseBookEntity expenseBookEntity) {
        mExpenseBookRepository.updateExpenseBook(expenseBookEntity);
    }

    @Transactional
    private List<MemberEntity> createMembers(List<Member> memberList) {
        if (memberList == null || memberList.isEmpty()) {
            throw new WebApplicationException("Invalid Member data");
        } else {
            List<MemberEntity> memberEntities = new ArrayList<>();
            mEntityModelConverter.toEntity(memberList).forEach(memberEntity -> {
                try {
                    memberEntities.add(mMemberRepository.getMemberByEmail(memberEntity.getMemberEmail()));
                } catch (EmptyResultDataAccessException e) {
                    memberEntities.add(mMemberRepository.createMember(memberEntity));
                }
            });
            return memberEntities;
        }
    }

    private ExpenseBookEntity getExpenseBookEntity(ExpenseBook expenseBook, ExpenseBookEntity expenseBookEntity) {
        try {
           /* expenseBookEntity = mExpenseBookRepository.getExpenseBookByMember(expenseBook.getClientId(),
                    expenseBook.getOwnerEmailId());*/
        } catch (EmptyResultDataAccessException e) {
            //no need to log;
        }
        return expenseBookEntity;
    }


    @Transactional
    public void updateExpenseBook(ExpenseBook expenseBook) {
        mExpenseBookRepository.updateExpenseBook(mExpenseBookEntityModelConverter.toEntity(expenseBook));
    }


    public ExpenseBook getExpenseBookDetails(String clientId) {
        return mExpenseBookEntityModelConverter.toModel(mExpenseBookRepository.getExpenseBookDetails(clientId));
    }

    public List<ExpenseBook> getExpenseBookList(String memberEmailId) {
        return (List<ExpenseBook>) mExpenseBookEntityModelConverter.toModel(mExpenseBookRepository.getExpenseBook(memberEmailId));
    }

    @Transactional
    public void deleteMember(String clientId, String memberEmailId) {
        ExpenseBookEntity expenseBookEntity = mExpenseBookRepository.getExpenseBookDetails(clientId);
        Collection<MemberEntity> collection = expenseBookEntity.getMemberList();

        collection.forEach(memberEntity -> {
            if (memberEntity.getMemberEmail().equals(memberEmailId)) {
                collection.remove(memberEntity);
            }
        });
        mExpenseBookRepository.updateExpenseBook(expenseBookEntity);
    }

    @Override
    public void deleteExpenseBook(String clientId) {
        mExpenseBookRepository.deleteExpenseBook(clientId);
    }


    @Override
    public void addMembers(List<Member> memberList, String clientId) {
        List<MemberEntity> memberEntities = createMembers(memberList);
        //TODO : Handle no expense book present case
        ExpenseBookEntity expenseBookEntity = mExpenseBookRepository.getExpenseBookDetails(clientId);
        expenseBookEntity.getMemberList().addAll(memberEntities);
        addM(expenseBookEntity);
    }

    @Override
    @Transactional
    public void addMembers(List<Member> memberList, ExpenseBookEntity expenseBookEntity) {
        log.debug("<< addMembers");
        List<MemberEntity> entities = createMembers(memberList);
        expenseBookEntity.setMemberList(new HashSet<>(entities));
        mExpenseBookRepository.updateExpenseBook(expenseBookEntity);
        log.debug(">> addMembers");
    }

    @Override
    public void addMember(String clientId, Member member) {
        log.debug("<< addMember");
        MemberEntity memberEntity = null;
        try {
            memberEntity = mMemberRepository.getMemberByEmail(member.getMemberEmail());
        } catch (NoResultException e) {
            log.info("Member is not present");
            memberEntity = createMember(member);
        } catch (EmptyResultDataAccessException e) {
            log.info("Member is not present");
            memberEntity = createMember(member);
        }
        //TODO: Handle EmptyResultDataAccessException here
        ExpenseBookEntity expenseBookEntity = mExpenseBookRepository.getExpenseBookDetails(clientId);
        expenseBookEntity.getMemberList().add(memberEntity);
        addM(expenseBookEntity);

        log.debug(">> addMember");
    }


    @Transactional
    private MemberEntity createMember(Member member) {
        return mMemberRepository.createMember(mEntityModelConverter.toEntity(member));
    }


    @Transactional
    public void addMember(ExpenseBookEntity clientId, Member member) {
        log.debug("<< addMember");
        MemberEntity memberEntity = null;
        try {
            memberEntity = mMemberRepository.getMemberByEmail(member.getMemberEmail());
        } catch (NoResultException e) {
            log.info("Member is not present");
            memberEntity = mMemberRepository.createMember(mEntityModelConverter.toEntity(member));
        } catch (EmptyResultDataAccessException e) {
            log.info("Member is not present");
            memberEntity = mMemberRepository.createMember(mEntityModelConverter.toEntity(member));
        }
        mExpenseBookRepository.addMember(clientId, memberEntity);
        log.debug(">> addMember");
    }


    public List<ExpenseBook> getExpenseBookList(long memberId) {
        return (List<ExpenseBook>) mExpenseBookEntityModelConverter.toModel(mExpenseBookRepository.getExpenseBook(memberId));
    }

}

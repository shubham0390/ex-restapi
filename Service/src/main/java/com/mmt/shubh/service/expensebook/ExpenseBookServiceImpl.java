package com.mmt.shubh.service.expensebook;

import com.mmt.shubh.entity.ExpenseBookEntity;
import com.mmt.shubh.entity.MemberEntity;
import com.mmt.shubh.repository.expensebook.IExpenseBookRepository;
import com.mmt.shubh.repository.member.IMemberRepository;
import com.mmt.shubh.rest.model.ExpenseBook;
import com.mmt.shubh.rest.model.Member;
import com.mmt.shubh.service.converter.IEntityModelConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/9/2015.
 * <p>
 * TODO: Add class comments
 */
@Component
@Slf4j
public class ExpenseBookServiceImpl implements IExpenseBookService {


    @Autowired
    @Qualifier(value = "expenseBookRepositoryImpl")
    IExpenseBookRepository mExpenseBookRepository;

    @Qualifier("memberRepositoryImpl")
    @Autowired
    IMemberRepository mMemberRepository;

    @Qualifier("expenseBookEntityModelConverter")
    @Autowired
    IEntityModelConverter<ExpenseBookEntity, ExpenseBook> mExpenseBookEntityModelConverter;

    @Qualifier("memberEntityModelConverter")
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
            throw new WebApplicationException("Expense book already present with following name "
                    + expenseBook.getName() + "for following member" + expenseBook.getOwnerEmailId());
        }


        log.debug("ENDS createExpenseBook");
    }

    @Transactional
    private void addM(ExpenseBookEntity expenseBookEntity) {
        mExpenseBookRepository.updateExpenseBook(expenseBookEntity);
    }

    @Transactional
    private List<MemberEntity> createMembers(List<Member> memberList) {
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

    private ExpenseBookEntity getExpenseBookEntity(ExpenseBook expenseBook, ExpenseBookEntity expenseBookEntity) {
        try {
            expenseBookEntity = mExpenseBookRepository.getExpenseBookByMember(expenseBook.getClientId(),
                    expenseBook.getOwnerEmailId());
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
        return mExpenseBookEntityModelConverter.toModel(mExpenseBookRepository.getExpenseBook(memberEmailId));
    }

    public void deleteMember(String clientId) {
        mExpenseBookRepository.deleteMember(clientId);
    }

    @Override
    public void deleteExpenseBook(String clientId) {
        mExpenseBookRepository.deleteExpenseBook(clientId);
    }


    @Override
    public void addMembers(List<Member> memberList, String clientId) {

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
        return mExpenseBookEntityModelConverter.toModel(mExpenseBookRepository.getExpenseBook(memberId));
    }

}

package com.mmt.shubh.service.expensebook;

import com.mmt.shubh.entity.ExpenseBookEntity;
import com.mmt.shubh.entity.MemberEntity;
import com.mmt.shubh.repository.expensebook.IExpenseBookRepository;
import com.mmt.shubh.repository.member.IMemberRepository;
import com.mmt.shubh.rest.model.ExpenseBook;
import com.mmt.shubh.rest.model.Member;
import com.mmt.shubh.rest.response.ServiceResponse;
import com.mmt.shubh.service.converter.EntityModelConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/9/2015.
 * <p>
 * TODO: Add class comments
 */
@Component
@Slf4j
@Transactional
public class ExpenseBookServiceImpl implements IExpenseBookService {


    @Autowired
    @Qualifier(value = "expenseBookRepositoryImpl")
    IExpenseBookRepository mExpenseBookRepository;

    @Qualifier("memberRepositoryImpl")
    @Autowired
    IMemberRepository mMemberRepository;

    @Qualifier("expenseBookEntityModelConverter")
    @Autowired
    EntityModelConverter<ExpenseBookEntity, ExpenseBook> mBookEntityModelConverter;

    @Qualifier("memberEntityModelConverter")
    @Autowired
    EntityModelConverter<MemberEntity, Member> mEntityModelConverter;

    public ServiceResponse createExpenseBook(ExpenseBook expenseBook) {
        mExpenseBookRepository.createExpenseBook(mBookEntityModelConverter.toEntity(expenseBook));
        return null;
    }

    public ServiceResponse updateExpenseBook(ExpenseBook expenseBook) {
        mExpenseBookRepository.updateExpenseBook(mBookEntityModelConverter.toEntity(expenseBook));
        return null;
    }

    public ServiceResponse addMember(long id, Member member) {
        return null;
    }

    public ServiceResponse getExpenseBookDetails(long id) {
       // mExpenseBookRepository.getExpenseBookDetails(id);
        return null;
    }

    public ServiceResponse getExpenseBookList(String memberEmailId) {
        //mExpenseBookRepository.getExpenseBook(memberEmailId);
        return null;
    }

    public ServiceResponse deleteMember(long id) {
        //mExpenseBookRepository.deleteMember(id);
        return null;
    }

    @Override
    public ServiceResponse deleteExpenseBook(long id) {
        //mExpenseBookRepository.deleteExpenseBook(id);
        return null;
    }

    public ServiceResponse addMembers(List<Member> memberList, long expenseBookId) {
        return null;
    }

    public ServiceResponse getExpenseBookList(long memberId) {
        //mExpenseBookRepository.getExpenseBook(memberId);
        return null;
    }

}

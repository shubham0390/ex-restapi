package com.mmt.shubh.rest.resources.expensebook;

import com.mmt.shubh.rest.model.ExpenseBook;
import com.mmt.shubh.rest.model.Member;
import com.mmt.shubh.service.expensebook.IExpenseBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
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

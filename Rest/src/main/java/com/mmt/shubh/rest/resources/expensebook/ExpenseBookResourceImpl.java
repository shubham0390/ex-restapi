package com.mmt.shubh.rest.resources.expensebook;

import com.mmt.shubh.rest.model.ExpenseBook;
import com.mmt.shubh.rest.model.Member;
import com.mmt.shubh.service.expensebook.IExpenseBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
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
    IExpenseBookService mExpenseBookService;

    @Override
    public Response createExpenseBook(ExpenseBook expenseBook) {
        mExpenseBookService.createExpenseBook(expenseBook);
        return null;
    }

    @Override
    public Response updateExpenseBook(ExpenseBook expenseBook) {
        mExpenseBookService.updateExpenseBook(expenseBook);
        return null;
    }

    @Override
    public Response addMember(Member member, long expenseBookId) {
        mExpenseBookService.addMember(expenseBookId, member);
        return null;
    }

    @Override
    public Response addMembers(List<Member> memberList, long expenseBookId) {
        mExpenseBookService.addMembers(memberList, expenseBookId);
        return null;
    }


    @Override
    public Response getExpenseBookDetails(long id) {
        mExpenseBookService.getExpenseBookDetails(id);
        return null;
    }

    @Override
    public Response getExpenseBookList(String memberEmailId) {
        mExpenseBookService.getExpenseBookList(memberEmailId);
        return null;
    }

    @Override
    public Response getExpenseBookList(long memberId) {
        mExpenseBookService.getExpenseBookList(memberId);
        return null;
    }

    @Override
    public Response deleteMember(long id) {
        mExpenseBookService.deleteMember(id);
        return null;
    }

    @Override
    public Response deleteExpenseBook(long id) {
        mExpenseBookService.deleteExpenseBook(id);
        return null;
    }

}

package com.mmt.shubh.service.expensebook;

import com.mmt.shubh.database.entity.ExpenseBookEntity;
import com.mmt.shubh.rest.model.ExpenseBook;
import com.mmt.shubh.rest.model.Member;
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

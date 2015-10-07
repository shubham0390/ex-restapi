package com.mmt.shubh.service.expensebook;

import com.mmt.shubh.rest.model.ExpenseBook;
import com.mmt.shubh.rest.model.Member;
import com.mmt.shubh.rest.response.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/9/2015.
 * <p>
 * TODO: Add class comments
 */
@Service
public interface IExpenseBookService {

    ServiceResponse createExpenseBook(ExpenseBook expenseBook);

    ServiceResponse updateExpenseBook(ExpenseBook expenseBook);

    ServiceResponse addMember(long id, Member member);

    ServiceResponse getExpenseBookDetails(long id);

    ServiceResponse getExpenseBookList(String memberEmailId);

    ServiceResponse deleteMember(long id);

    ServiceResponse deleteExpenseBook(long id);

    ServiceResponse addMembers(List<Member> memberList, long expenseBookId);

    ServiceResponse getExpenseBookList(long memberId);

}

package com.mmt.shubh.repository.expensebook;


import com.mmt.shubh.entity.ExpenseBookEntity;
import com.mmt.shubh.entity.MemberEntity;
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

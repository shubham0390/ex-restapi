package com.mmt.shubh.repository.expensebook;


import com.mmt.shubh.entity.ExpenseBookEntity;
import com.mmt.shubh.entity.MemberEntity;
import com.mmt.shubh.exception.DuplicateEntityException;
import com.mmt.shubh.exception.EntityNotFountException;
import com.mmt.shubh.exception.InvalidEntityException;
import com.mmt.shubh.exception.UnrecoverableException;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/9/2015.
 * <p>
 * TODO: Add class comments
 */
public interface IExpenseBookRepository extends Repository<ExpenseBookEntity,Long>{

    void createExpenseBook(ExpenseBookEntity expenseBookEntity) throws DuplicateEntityException
            , InvalidEntityException, UnrecoverableException;

    void updateExpenseBook(ExpenseBookEntity expenseBookEntity) throws EntityNotFountException,
            UnrecoverableException, InvalidEntityException;

    void addMember(long id, MemberEntity memberEntity);

    ExpenseBookEntity getExpenseBookDetails(long id)throws EntityNotFountException;

    void deleteMember(long id);

    void deleteExpenseBook(long id);

    List<ExpenseBookEntity> getExpenseBookListByMemberEmail(String memberEmailId);

    List<ExpenseBookEntity> getExpenseBookListByMemberId(long memberId);
}

package com.mmt.shubh.repository.expensebook;

import com.mmt.shubh.BaseRepository;
import com.mmt.shubh.entity.ExpenseBookEntity;
import com.mmt.shubh.entity.MemberEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/9/2015.
 * <p>
 * TODO: Add class comments
 */
@Component
public class ExpenseBookRepositoryImpl extends BaseRepository<ExpenseBookEntity> implements IExpenseBookRepository {

    @Override
    public void createExpenseBook(ExpenseBookEntity expenseBookEntity) {
        create(expenseBookEntity);
    }

    @Override
    public void updateExpenseBook(ExpenseBookEntity expenseBookEntity) {
        update(expenseBookEntity);
    }

    /*@Override
    public void addMember(long id, MemberEntity memberEntity) {

    }

    @Override
    public ExpenseBookEntity getExpenseBookDetails(long id) {
        return findOne(id);
    }

    @Override
    public void deleteMember(long id) {

    }

    @Override
    public void deleteExpenseBook(long id) {
        deleteById(id);
    }

    @Override
    public List<ExpenseBookEntity> getExpenseBook(String memberEmailId) {
       *//* String s = "SELECT eb FROM ExpenseBookEntity eb " +
                "INNER JOIN eb.memberList member " +
                "WHERE member.member_email = :member_email";

        entityManager.cre
        Query query = session.createQuery(hql);
        query.setParameterList("tags", tags);
        query.setInteger("tag_count", tags.length);
        List<Article> articles = query.list();*//*
        return null;
    }

    @Override
    public List<ExpenseBookEntity> getExpenseBook(long memberId) {
        return null;
    }*/

}

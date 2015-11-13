package com.mmt.shubh.repository.expensebook;

import com.mmt.shubh.BaseRepository;
import com.mmt.shubh.entity.ExpenseBookEntity;
import com.mmt.shubh.entity.ExpenseBookEntity_;
import com.mmt.shubh.entity.MemberEntity;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Metamodel;
import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/9/2015.
 * <p>
 * TODO: Add class comments
 */
@Component
public class ExpenseBookRepositoryImpl extends BaseRepository<ExpenseBookEntity> implements IExpenseBookRepository {

    public ExpenseBookRepositoryImpl() {
        setClazz(ExpenseBookEntity.class);
    }

    @Override
    public void createExpenseBook(ExpenseBookEntity expenseBookEntity) {
        create(expenseBookEntity);
    }


    @Override
    public void updateExpenseBook(ExpenseBookEntity expenseBookEntity) {
        update(expenseBookEntity);
    }

    @Override
    public void addMember(String clientId, MemberEntity memberEntity) {
        ExpenseBookEntity expenseBookEntity = getExpenseBookDetails(clientId);
        expenseBookEntity.getMemberList().add(memberEntity);
        updateExpenseBook(expenseBookEntity);
    }

    @Override
    public void addMember(ExpenseBookEntity expenseBookEntity, MemberEntity memberEntity) {
        expenseBookEntity.getMemberList().add(memberEntity);
        updateExpenseBook(expenseBookEntity);
    }

    @Override
    public ExpenseBookEntity getExpenseBookDetails(String clientId) {
        return findByColumnNameAndStringValue(ExpenseBookEntity_.clientId, clientId);
    }

    @Override
    public void deleteMember(String clientId) {

    }

    @Override
    public void deleteExpenseBook(String clientId) {
        delete(getExpenseBookDetails(clientId));
    }

    @Override
    public ExpenseBookEntity getExpenseBookByMember(String clientId, String memberEmail) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ExpenseBookEntity> query = builder.createQuery(ExpenseBookEntity.class);

        Metamodel metamodel = entityManager.getMetamodel();
        Root<ExpenseBookEntity> entityRoot = query.from(metamodel.entity(ExpenseBookEntity.class));
        query.select(entityRoot);
        query.where(
                builder.and(
                        builder.equal(
                                entityRoot.get(ExpenseBookEntity_.clientId), clientId
                        ),
                        builder.equal(
                                entityRoot.get(ExpenseBookEntity_.ownerEmailId), memberEmail

                        )
                )
        );
        return entityManager.createQuery(query).getSingleResult();
    }


    @Override
    public List<ExpenseBookEntity> getExpenseBook(String memberEmailId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ExpenseBookEntity> query = builder.createQuery(ExpenseBookEntity.class);

        Metamodel metamodel = entityManager.getMetamodel();
        Root<ExpenseBookEntity> entityRoot = query.from(metamodel.entity(ExpenseBookEntity.class));
        query.select(entityRoot);
        query.where(builder.equal(entityRoot.get(ExpenseBookEntity_.ownerEmailId), memberEmailId)

        );
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    //TODO : learn joins in JPA
    public List<ExpenseBookEntity> getExpenseBook(long memberId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ExpenseBookEntity> query = builder.createQuery(ExpenseBookEntity.class);
        Metamodel metamodel = entityManager.getMetamodel();
       String s = "SELECT * FROM expensebookentity where expensebookentity.id in(SELECT expensebook_id FROM expensebook_member " +
                "where member_id = 2)";

        Root<ExpenseBookEntity> entityRoot = query.from(metamodel.entity(ExpenseBookEntity.class));
        Join<ExpenseBookEntity,MemberEntity> join = entityRoot.join(ExpenseBookEntity_.memberList);

        query.select(entityRoot);
        query.where(builder.equal(entityRoot.get(ExpenseBookEntity_.memberList).in(memberId),memberId)

        );
        return entityManager.createQuery(query).getResultList();
    }

}

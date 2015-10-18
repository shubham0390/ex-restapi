package com.mmt.shubh.repository.member;

import com.mmt.shubh.BaseRepository;
import com.mmt.shubh.entity.MemberEntity;
import com.mmt.shubh.entity.MemberEntity_;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
@Repository(value = "memberRepositoryImpl")
@Slf4j
public class MemberRepositoryImpl extends BaseRepository<MemberEntity> implements IMemberRepository {


    public MemberRepositoryImpl() {
        setClazz(MemberEntity.class);
    }

    public MemberEntity updateMember(MemberEntity memberEntity) {
        log.debug("UPDATE MEMBER STARTS");
        MemberEntity memberEntity1 = update(memberEntity);
        entityManager.flush();
        log.debug("UPDATE MEMBER END");

        return memberEntity1;

    }

    public void deleteMember(long id) {
        log.debug("deleteMember STARTS");
        deleteById(id);
        log.debug("deleteMember ENDS");
    }

    public void deleteMemberByEmailId(String emailId) {
        log.debug("deleteMemberByEmailId STARTS");
        MemberEntity memberEntity = findByColumnNameAndStringValue(MemberEntity_.memberEmail, emailId);
        delete(memberEntity);
        log.debug("deleteMemberByEmailId ENDS");
    }

    public MemberEntity createMember(MemberEntity member) {
        log.debug("CREATE MEMBER STARTS");
        create(member);
        entityManager.flush();
        log.debug("CREATE MEMBER STARTS");
        return member;
    }

    @Override
    public List<MemberEntity> getMembersByExpenseBook(long expenseBookId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<MemberEntity> query = builder.createQuery(MemberEntity.class);

        Metamodel metamodel = entityManager.getMetamodel();

        EntityType<MemberEntity> entityType = metamodel.entity(MemberEntity.class);

        Root<MemberEntity> root = query.from(entityType);

        query.select(root);

        /*//TODO: add condition
        query.where(builder.equal(
                root.get(MemberEntity_.expenseBook), expenseBookId));*/

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public MemberEntity getMemberById(long memberId) {
        log.debug("START METHOD getMemberById");
        MemberEntity memberEntity = findByColumnNameAndLongValue(MemberEntity_.id, memberId);
        log.debug("END METHOD getMemberById");
        return memberEntity;
    }

    @Override
    public MemberEntity getMemberByEmail(String emailId) {
        log.debug("START METHOD getMemberByEmail");
        MemberEntity memberEntity = findByColumnNameAndStringValue(MemberEntity_.memberEmail, emailId);
        log.debug("END METHOD getMemberByEmail");
        return memberEntity;
    }

    @Override
    public List<MemberEntity> createMembers(List<MemberEntity> memberList) {
        return null;
    }
}

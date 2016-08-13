/*
 * Copyright (c) 2016. . The Km2Labs Project
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */

package com.km2labs.expenseview.database.repository.member;

import com.km2labs.expenseview.database.entity.MemberEntity;
import com.km2labs.expenseview.database.entity.MemberEntity_;
import com.km2labs.expenseview.database.repository.BaseRepository;
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
@Repository(value = "memberSQLRepository")
@Slf4j
public class MemberSQLRepository extends BaseRepository<MemberEntity> implements IMemberRepository {


    public MemberSQLRepository() {
        setClazz(MemberEntity.class);
    }

    public MemberEntity updateMember(MemberEntity memberEntity) {
        log.debug("UPDATE MEMBER STARTS");
        MemberEntity memberEntity1 = update(memberEntity);
        mEntityManager.flush();
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
        MemberEntity memberEntity = findByColumnNameAndValue(MemberEntity_.memberEmail, emailId);
        delete(memberEntity);
        log.debug("deleteMemberByEmailId ENDS");
    }

    public MemberEntity createMember(MemberEntity member) {
        log.debug("CREATE MEMBER STARTS");
        create(member);
        mEntityManager.flush();
        log.debug("CREATE MEMBER STARTS");
        return member;
    }

    @Override
    public List<MemberEntity> getMembersByExpenseBook(long expenseBookId) {
        CriteriaBuilder builder = mEntityManager.getCriteriaBuilder();

        CriteriaQuery<MemberEntity> query = builder.createQuery(MemberEntity.class);

        Metamodel metamodel = mEntityManager.getMetamodel();

        EntityType<MemberEntity> entityType = metamodel.entity(MemberEntity.class);

        Root<MemberEntity> root = query.from(entityType);

        query.select(root);

        //TODO: add condition
        query.where(builder.equal(
                root.get(MemberEntity_.expenseBook), expenseBookId));

        return mEntityManager.createQuery(query).getResultList();
    }

    @Override
    public MemberEntity getMemberById(long memberId) {
        log.debug("START METHOD getMemberById");
        MemberEntity memberEntity = findByColumnNameAndValue(MemberEntity_.id, memberId);
        log.debug("END METHOD getMemberById");
        return memberEntity;
    }

    @Override
    public MemberEntity getMemberByEmail(String emailId) {
        log.debug("START METHOD getMemberByEmail");
        MemberEntity memberEntity = findByColumnNameAndValue(MemberEntity_.memberEmail, emailId);
        log.debug("END METHOD getMemberByEmail");
        return memberEntity;
    }

    @Override
    public List<MemberEntity> createMembers(List<MemberEntity> memberList) {
        return null;
    }

    @Override
    public MemberEntity getMemberUniqueId(String uniqueId) {
        return null;
    }
}

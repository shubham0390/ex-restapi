package com.mmt.shubh.repository.member;

import com.mmt.shubh.entity.MemberEntity;
import com.mmt.shubh.exception.DuplicateEntityException;
import com.mmt.shubh.exception.InvalidEntityException;
import com.mmt.shubh.exception.UnrecoverableException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
@Service
@Scope(value = "singleton")
public interface IMemberRepository {

    MemberEntity updateMember(MemberEntity memberEntity);

    void deleteMember(long id);

    void deleteMemberByEmailId(String emailId);

    MemberEntity createMember(MemberEntity member) throws DuplicateEntityException
            , InvalidEntityException, UnrecoverableException;

    List<MemberEntity> getMembersByExpenseBook(long expenseBookId);
}

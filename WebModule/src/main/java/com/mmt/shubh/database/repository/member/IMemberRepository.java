package com.mmt.shubh.database.repository.member;

import com.mmt.shubh.database.entity.MemberEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */

@Repository
@Scope(value = "singleton")
public interface IMemberRepository {

    MemberEntity updateMember(MemberEntity memberEntity);

    void deleteMember(long id);

    void deleteMemberByEmailId(String emailId);

    MemberEntity createMember(MemberEntity member) ;

    List<MemberEntity> getMembersByExpenseBook(long expenseBookId);

    MemberEntity getMemberById(long memberId) ;


    MemberEntity getMemberByEmail(String emailId);

    List<MemberEntity> createMembers(List<MemberEntity> memberList);

    MemberEntity getMemberUniqueId(String uniqueId);
}

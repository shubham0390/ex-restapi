package com.mmt.shubh.service.member;

import com.mmt.shubh.rest.model.Member;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
@Service
@Scope(value = "prototype")
public interface IMemberService {

    Member updateMember(Member member);

    long deleteMember(long id);

    Member getMember(String emailId);

    Member registerMember(Member member) ;

    String deleteMember(String emailId);

    List<Member> getExpenseBookMember(long expenseBookId);

    List<Member> createMembers(List<Member> memberEntities);

    Member getMemberByEmail(String memberEmail);

    Member createMember(Member memberEntity);

    Member getMemberById(long memberServerId);

    String generateAccessToken(String memberEmail);

    String login(Member member);

    void logout(String emailId);
}

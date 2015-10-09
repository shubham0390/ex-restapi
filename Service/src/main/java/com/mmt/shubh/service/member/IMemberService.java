package com.mmt.shubh.service.member;

import com.mmt.shubh.exception.DuplicateEntityException;
import com.mmt.shubh.exception.InvalidEntityException;
import com.mmt.shubh.exception.UnrecoverableException;
import com.mmt.shubh.rest.model.Member;
import com.mmt.shubh.rest.response.ServiceResponse;
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

    Member registerMember(Member member) throws DuplicateEntityException, InvalidEntityException, UnrecoverableException;

    String deleteMember(String emailId);

    List<Member> getExpenseBookMember(long expenseBookId);
}

package com.mmt.shubh.service.member;

import com.mmt.shubh.rest.model.Member;
import com.mmt.shubh.rest.response.ServiceResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
@Service
@Scope(value = "prototype")
public interface IMemberService {

    ServiceResponse updateMember(Member member);

    ServiceResponse deleteMember(long id);

    ServiceResponse getMember(String emailId);

    ServiceResponse registerMember(Member member);

    ServiceResponse deleteMember(String emailId);
}

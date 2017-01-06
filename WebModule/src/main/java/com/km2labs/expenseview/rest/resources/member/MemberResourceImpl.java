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

package com.km2labs.expenseview.rest.resources.member;

import com.km2labs.expenseview.rest.dto.Member;
import com.km2labs.expenseview.service.member.IMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
@Slf4j
@Service
public class MemberResourceImpl implements IMemberResource {

    private final IMemberService mMemberService;

    @Autowired()
    public MemberResourceImpl(@Qualifier(value = "memberServiceImpl") IMemberService mMemberService) {
        this.mMemberService = mMemberService;
    }

    @Override
    public Member updateMember(Member member) {
        return mMemberService.updateMember(member);
    }


    @Override
    public Member getMember(String emailId) {
        return null;
    }


    @Override
    public String deleteMember(String emailId) {
        return mMemberService.deleteMember(emailId);
    }

    @Override
    public List<Member> getExpenseBookMembers(long expenseBookId) {
        return mMemberService.getExpenseBookMember(expenseBookId);
    }

    @Override
    public Response syncMember(long expenseBookId, long syncId) {
        return null;
    }

    @Override
    public String getDemo() {
        return "Hello how are you";
    }


}

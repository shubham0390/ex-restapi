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

package com.km2labs.expenseview.service.member;

import com.km2labs.expenseview.rest.dto.Member;
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

    Member registerMember(Member member) ;

    String deleteMember(String emailId);

    List<Member> getExpenseBookMember(long expenseBookId);

    List<Member> createMembers(List<Member> memberEntities);

    Member getMemberByEmail(String memberEmail);

    Member createMember(Member memberEntity);

    Member getMemberById(long memberServerId);



}

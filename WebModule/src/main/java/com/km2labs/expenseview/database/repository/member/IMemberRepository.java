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

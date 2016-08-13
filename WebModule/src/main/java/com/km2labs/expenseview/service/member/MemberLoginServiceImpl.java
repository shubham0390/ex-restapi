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

import com.km2labs.expenseview.database.entity.MemberEntity;
import com.km2labs.expenseview.database.repository.member.IMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Created by Subham on 18/05/16.
 */
@Slf4j
@Component
public class MemberLoginServiceImpl implements IMemberLoginService {

    @Qualifier(value = "memberSQLRepository")
    @Autowired
    IMemberRepository mMemberRepository;

    @Qualifier("memberOTPServiceImpl")
    @Autowired
    IMemberTokenService mMemberOTPService;

    @Override
    public String login(String uniqueId) {
        boolean isRegistered = false;

        MemberEntity memberEntity = null;
        try {
            memberEntity = mMemberRepository.getMemberUniqueId(uniqueId);
        } catch (NoResultException e) {
            log.info("Member is not present");

        } catch (EmptyResultDataAccessException e) {
            log.info("Member is not present");
        }

        if (memberEntity != null) {
            isRegistered = memberEntity.isRegistered();
        } else {
            throw new WebApplicationException(Response.status(Response.Status.NO_CONTENT)
                    .entity("Member is not Registered").build());
        }

        if (!isRegistered) {
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Member is not verified").build());
        }
        String emailId = memberEntity.getMemberEmail();
        return mMemberOTPService.generateAccessToken(emailId);
    }

    @Override
    public void logout(String emailId) {

    }
}

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

import com.km2labs.expenseview.database.repository.member.IMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Subham on 18/05/16.
 */
@Component(value = "memberOTPServiceImpl")
@Slf4j
@Transactional
public class MemberTokenServiceImpl implements IMemberTokenService {

    @Qualifier("memberSQLRepository")
    @Autowired(required = false)
    IMemberRepository mMemberRepository;

    @Qualifier("memberSetupService")
    @Autowired
    IMemberSetupService mMemberSetupService;

    @Override
    public void generateOTPService(long memberId, long deviceId) {

    }

    @Override
    public boolean verifyOTP(int otp, long memberId, long deviceId) {
        //mMemberSetupService.setup(memberId);
        return false;
    }

    @Override
    public String generateAccessToken(String memberEmail) {
        return null;
    }
}

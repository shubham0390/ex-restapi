package com.mmt.shubh.service.member;

import com.mmt.shubh.database.repository.member.IMemberRepository;
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

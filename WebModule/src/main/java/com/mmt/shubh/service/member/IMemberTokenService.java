package com.mmt.shubh.service.member;

import org.springframework.stereotype.Service;

/**
 * Created by Subham on 18/05/16.
 */
@Service
public interface IMemberTokenService {

    void generateOTPService(long memberId, long deviceId);

    boolean verifyOTP(int otp, long memberId, long deviceId);

    String generateAccessToken(String memberEmail);
}

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

package com.km2labs.expenseview.service.user;

import com.km2labs.expenseview.database.entity.DeviceEntity;
import com.km2labs.expenseview.database.entity.UserEntity;
import com.km2labs.expenseview.database.entity.UserOTPEntity;
import com.km2labs.expenseview.database.repository.otp.IOTPRepository;
import com.km2labs.expenseview.exception.AuthenticationException;
import com.km2labs.expenseview.service.TOTP;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class OTPService implements IOTPService {

    //Base url to send sms
    private static final String SEND_SMS_URL = "http://smsgateway.me/api/v3/messages/send";

    private static final String KEY_MESSAGE = "message";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_DEVICE = "device";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    private final IOTPRepository mOTpRepository;

    @Autowired
    public OTPService(@Qualifier(value = "OTPSQLRepository") IOTPRepository repository) {
        this.mOTpRepository = repository;
    }


    //// TODO: 13/08/16 Generate otp in background thread
    @Override
    public void generateOtp(UserEntity userEntity, DeviceEntity deviceEntity) {
        String otp = null;
        DateTime dateTime = new DateTime();
        long currentTime = dateTime.getMillis();
        otp = getOTP(null, currentTime);

        if (otp != null) {
            UserOTPEntity userOTPEntity = new UserOTPEntity();
            userOTPEntity.setDeviceEntity(deviceEntity);
            userOTPEntity.setUserEntity(userEntity);
            userOTPEntity.setOtp(Integer.parseInt(otp));
            userOTPEntity.setGeneratedTime(currentTime);
            userOTPEntity.setExpirationTime(dateTime.plusMinutes(5).getMillis());
            mOTpRepository.save(userOTPEntity);
            sendSms(otp, Integer.parseInt(userEntity.getPhoneNumber()));
        }
    }

    private String getOTP(String otp, long currentTime) {
        String seed = "3132333435363738393031323334353637383930";
        long T0 = 0;
        long X = 30;
        long testTime[] = {currentTime};

        String steps;

        try {
            for (long aTestTime : testTime) {
                long T = (aTestTime - T0) / X;
                steps = Long.toHexString(T).toUpperCase();

                while (steps.length() < 16) steps = "0" + steps;
                otp = TOTP.generateTOTP(seed, steps, "6");

            }
        } catch (final Exception e) {
            System.out.println("Error : " + e);
        }
        return otp;
    }

    private void sendSms(String otp, int phoneNumber) {
        //Replace DEMO_USERNAME with username of your account
        String username = "subhamtyagi@km2labs.com";
        //Replace DEMO_PASSWORD with password of your account
        String password = "Subham@123";
        String deviceId = "26279";

        Header header = new BasicHeader(KEY_EMAIL, username);
        Header header1 = new BasicHeader(KEY_PASSWORD, password);

        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair(KEY_NUMBER, String.valueOf(phoneNumber)));
        nvps.add(new BasicNameValuePair(KEY_MESSAGE, "Expense View one time password is " + otp));
        nvps.add(new BasicNameValuePair(KEY_DEVICE, deviceId));
        nvps.add(new BasicNameValuePair(KEY_EMAIL, username));
        nvps.add(new BasicNameValuePair(KEY_PASSWORD, password));

        HttpPost httpost = new HttpPost(SEND_SMS_URL);
        httpost.addHeader(header);
        httpost.addHeader(header1);
        HttpClient client = HttpClientBuilder.create().build();

        try {
            httpost.setEntity(new UrlEncodedFormEntity(nvps));
            HttpResponse response = client.execute(httpost);
            StatusLine statusLine = response.getStatusLine();

            if (statusLine.getStatusCode() == 200) {
                log.info("SMS sent successfully");
            } else {
                log.info("SMS sent fail");
            }
        } catch (IOException e) {
            log.error("SMS sent fail " + e.getMessage());
        }
    }

    @Override
    public boolean verifyOTP(long userId, long deviceId, String otp) {
        UserOTPEntity otpEntity = mOTpRepository.findOne(userId, deviceId);
        long createdTime = otpEntity.getGeneratedTime();
        DateTime otpCreatedTime = new DateTime(createdTime);

        DateTime currentTime = new DateTime();
        currentTime.minusMinutes(5);

        if (otpCreatedTime.compareTo(currentTime) != 1) {
            throw new AuthenticationException("Otp has expired");
        }

        if (otpEntity.getOtp() != Integer.parseInt(otp)) {
            throw new AuthenticationException("Invalid otp");
        }
        return true;
    }
}

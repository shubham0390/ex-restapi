package com.mmt.shubh.service.user;

import com.mmt.shubh.database.entity.DeviceEntity;
import com.mmt.shubh.database.entity.UserEntity;
import com.mmt.shubh.database.entity.UserOTPEntity;
import com.mmt.shubh.database.repository.IOTPRepository;
import com.mmt.shubh.service.TOTP;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
public class OTPService implements IOTPService {

    private static final String SEND_SMS_URL = "http://smsgateway.me/api/v3/messages/send";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_DEVICE = "device";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    private final IOTPRepository mOTpRepository;

    @Autowired
    public OTPService(@Qualifier(value = "OTPRepository") IOTPRepository repository) {
        this.mOTpRepository = repository;
    }


    @Override
    public void generateOtp(UserEntity userEntity, DeviceEntity deviceEntity) {
        new Thread(() -> {
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

                sendSms(otp, userEntity.getPhoneNumber());
            }
        }).start();
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
        nvps.add(new BasicNameValuePair(KEY_MESSAGE, "Expense manager one time password is " + otp));
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
        } catch (UnsupportedEncodingException e) {
            log.error("SMS sent fail " + e.getMessage());
        } catch (ClientProtocolException e) {
            log.error("SMS sent fail " + e.getMessage());
        } catch (IOException e) {
            log.error("SMS sent fail " + e.getMessage());
        }

    }
}

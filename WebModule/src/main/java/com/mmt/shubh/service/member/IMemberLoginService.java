package com.mmt.shubh.service.member;

/**
 * Created by Subham on 18/05/16.
 */
public interface IMemberLoginService {
    String login(String phoneNumber);

    void logout(String emailId);
}

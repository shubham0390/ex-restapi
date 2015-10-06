package com.mmt.shubh.gcm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Subham Tyagi
 * On 6/3/2015.
 * <p>
 * TODO: Add class comments
 */
@Component
@Slf4j
public class GCMMessageService {

    private static final String SERVER_API_KEY = "AIzaSyCW5Uxelxh8x_3EuyH4EMbuqs8-GZr-AZU";

    private static final String SENDER_ID = "564755487737";

    private static final String COLLAPSE_KEY = "Update Data";

    private static final String APP_PACKAGE_NAME = "com.mmt.shubh.expensemanager";

    public void sendMessage(String registrationKey) {
        Message.Builder builder = new Message.Builder().collapseKey(COLLAPSE_KEY);
        builder.restrictedPackageName(APP_PACKAGE_NAME);
        builder.delayWhileIdle(true);
        builder.addData("shubham", "tyagi");

        GcmServerSideSender gcmServerSideSender = new GcmServerSideSender(SERVER_API_KEY);
        try {
            gcmServerSideSender.sendHttpJsonDownstreamMessage(registrationKey, builder.build());
        } catch (IOException e) {
            log.error("Unable to send message " + e.getMessage());
        }

    }
}

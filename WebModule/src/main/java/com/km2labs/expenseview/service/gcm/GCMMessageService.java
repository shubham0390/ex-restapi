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

package com.km2labs.expenseview.service.gcm;

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

    private static final String APP_PACKAGE_NAME = "com.km2labs.expenseview.expensemanager";

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

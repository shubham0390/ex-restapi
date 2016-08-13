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

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.util.Map;

/**
 * This class is used to send GCM downstream messages in the same way a server would.
 */
@Slf4j
public class GcmServerSideSender {

    private static final String GCM_SEND_ENDPOINT = "https://com.km2labs.expenseview.service.gcm-http.googleapis.com/com.km2labs.expenseview.service.gcm/send";

    private static final String UTF8 = "UTF-8";

    private static final String PARAM_TO = "to";
    private static final String PARAM_COLLAPSE_KEY = "collapse_key";
    private static final String PARAM_DELAY_WHILE_IDLE = "delay_while_idle";
    private static final String PARAM_TIME_TO_LIVE = "time_to_live";
    private static final String PARAM_DRY_RUN = "dry_run";
    private static final String PARAM_RESTRICTED_PACKAGE_NAME = "restricted_package_name";

    private static final String PARAM_PLAINTEXT_PAYLOAD_PREFIX = "data.";

    private static final String PARAM_JSON_PAYLOAD = "data";
    private static final String PARAM_JSON_NOTIFICATION_PARAMS = "notification";

    public static final String RESPONSE_PLAINTEXT_MESSAGE_ID = "id";
    public static final String RESPONSE_PLAINTEXT_CANONICAL_REG_ID = "registration_id";
    public static final String RESPONSE_PLAINTEXT_ERROR = "Error";

    private final String key;


    /**
     * @param key The API key used to authorize calls to Google
     */
    public GcmServerSideSender(String key) {
        this.key = key;
    }

    /**
     * Send a downstream message via HTTP plain text.
     *
     * @param destination the registration id of the recipient app.
     * @param message     the message to be sent
     * @throws IOException
     */
    public void sendHttpPlaintextDownstreamMessage(String destination, Message message)
            throws IOException {

        StringBuilder request = new StringBuilder();
        request.append(PARAM_TO).append('=').append(destination);
        addOptParameter(request, PARAM_DELAY_WHILE_IDLE, message.isDelayWhileIdle());
        addOptParameter(request, PARAM_DRY_RUN, message.isDryRun());
        addOptParameter(request, PARAM_COLLAPSE_KEY, message.getCollapseKey());
        addOptParameter(request, PARAM_RESTRICTED_PACKAGE_NAME, message.getRestrictedPackageName());
        addOptParameter(request, PARAM_TIME_TO_LIVE, message.getTimeToLive());
        for (Map.Entry<String, String> entry : message.getData().entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                String prefixedKey = PARAM_PLAINTEXT_PAYLOAD_PREFIX + entry.getKey();
                addOptParameter(request, prefixedKey, URLEncoder.encode(entry.getValue(), UTF8));
            }
        }

        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setHeader(HttpRequest.HEADER_CONTENT_TYPE, HttpRequest.CONTENT_TYPE_FORM_ENCODED);
        httpRequest.setHeader(HttpRequest.HEADER_AUTHORIZATION, "key=" + key);
        httpRequest.doPost(GCM_SEND_ENDPOINT, request.toString());

        if (httpRequest.getResponseCode() != 200) {
            throw new IOException("Invalid request."
                    + "\nStatus: " + httpRequest.getResponseCode()
                    + "\nResponse: " + httpRequest.getResponseBody());
        }

        String[] lines = httpRequest.getResponseBody().split("\n");
        if (lines.length == 0 || lines[0].equals("")) {
            throw new IOException("Received empty response from GCM service.");
        }

        String[] firstLineValues = lines[0].split("=");
        if (firstLineValues.length != 2) {
            throw new IOException("Invalid response from GCM: " + httpRequest.getResponseBody());
        }

        switch (firstLineValues[0]) {
            case RESPONSE_PLAINTEXT_MESSAGE_ID:
                log.info("Message sent.\nid: " + firstLineValues[1]);
                // check for canonical registration id
                if (lines.length > 1) {
                    // If the response includes a 2nd line we expect it to be the CANONICAL REG ID
                    String[] secondLineValues = lines[1].split("=");
                    if (secondLineValues.length == 2
                            && secondLineValues[0].equals(RESPONSE_PLAINTEXT_CANONICAL_REG_ID)) {
                        log.info("Message sent: canonical registration id = "
                                + secondLineValues[1]);
                    } else {
                        log.error("Invalid response from GCM."
                                + "\nResponse: " + httpRequest.getResponseBody());
                    }
                }
                break;
            case RESPONSE_PLAINTEXT_ERROR:
                log.error("Message failed.\nError: " + firstLineValues[1]);
                break;
            default:
                log.error("Invalid response from GCM."
                        + "\nResponse: " + httpRequest.getResponseBody());
                break;
        }
    }

    /**
     * Send a downstream message via HTTP JSON.
     *
     * @param destination the registration id of the recipient app.
     * @param message     the message to be sent
     * @throws IOException
     */
    public void sendHttpJsonDownstreamMessage(String destination,Message message) throws IOException {

        JsonFactory jsonFactory = new JsonFactory();
        OutputStream outputStream = new ByteArrayOutputStream();
        JsonGenerator jsonGenerator = jsonFactory.createGenerator(outputStream);

        jsonGenerator.writeStringField(PARAM_TO, destination);
        jsonGenerator.writeStringField(PARAM_COLLAPSE_KEY, message.getCollapseKey());
        jsonGenerator.writeStringField(PARAM_RESTRICTED_PACKAGE_NAME, message.getRestrictedPackageName());
        jsonGenerator.writeNumberField(PARAM_TIME_TO_LIVE, message.getTimeToLive());
        jsonGenerator.writeBooleanField(PARAM_DELAY_WHILE_IDLE, message.isDelayWhileIdle());
        jsonGenerator.writeBooleanField(PARAM_DRY_RUN, message.isDryRun());

        if (message.getData().size() > 0) {
            jsonGenerator.writeObjectFieldStart(PARAM_JSON_PAYLOAD);
            ObjectMapper objectMapper = new ObjectMapper();
            StringWriter stringWriter = new StringWriter();
            objectMapper.writeValue(stringWriter, message.getData());
            jsonGenerator.writeEndObject();
        }
        if (message.getNotificationParams().size() > 0) {
            jsonGenerator.writeObjectFieldStart(PARAM_JSON_NOTIFICATION_PARAMS);
            ObjectMapper objectMapper = new ObjectMapper();
            StringWriter stringWriter = new StringWriter();
            objectMapper.writeValue(stringWriter, message.getData());
            jsonGenerator.writeEndObject();
        }

        ObjectMapper mapper = new ObjectMapper();

        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setHeader(HttpRequest.HEADER_CONTENT_TYPE, HttpRequest.CONTENT_TYPE_JSON);
        httpRequest.setHeader(HttpRequest.HEADER_AUTHORIZATION, "key=" + key);
        httpRequest.doPost(GCM_SEND_ENDPOINT, outputStream.toString());

        if (httpRequest.getResponseCode() != 200) {
            throw new IOException("Invalid request."
                    + " status: " + httpRequest.getResponseCode()
                    + " response: " + httpRequest.getResponseBody());
        }

        /*try {
            jsonResponse = new JSONObject(httpRequest.getResponseBody());
            log.info("Send message:\n" + jsonResponse.toString(2));
        } catch (JSONException e) {
            log.error("Failed to parse server response:\n"
                    + httpRequest.getResponseBody());
        }*/
    }

    /**
     * Adds a new parameter to the HTTP POST body without doing any encoding.
     *
     * @param body  HTTP POST body.
     * @param name  parameter's name.
     * @param value parameter's value.
     */
    private static void addOptParameter(StringBuilder body, String name, Object value) {
        if (value != null) {
            String encodedValue = value.toString();
            if (value instanceof Boolean) {
                encodedValue = ((Boolean) value) ? "1" : "0";
            }
            body.append('&').append(name).append('=').append(encodedValue);
        }
    }
}
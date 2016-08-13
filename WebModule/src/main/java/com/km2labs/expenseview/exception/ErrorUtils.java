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

package com.km2labs.expenseview.exception;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;


@Slf4j
public class ErrorUtils {

    public static String logErrorAndGetRefId(Throwable cause) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String errorRefId = simpleDateFormat.format(new Date()) + "-" + UUID.randomUUID();
        if (cause == null) {
            cause = new Exception("No root cause exception given");
        }
        String message = cause.getMessage();
        log.error(errorRefId + " : " + message, cause);
        safeMonitoringLog(message, cause, errorRefId);
        return errorRefId;
    }

    private static void safeMonitoringLog(String message, Throwable cause, String errorRefId) {
        try {
            Map<String, Object> attrs = Maps.newLinkedHashMap();
            attrs.put("ref", errorRefId);
            attrs.put("msg", message);
            if (cause == null) {
                log.info(attrs.toString());
            } else {
                log.error(attrs.toString(), cause);
            }
        } catch (Exception e) {
            log.error("Failed to capture monitoring log for refId: " + errorRefId + ", cause: " + cause.getMessage(), e);
        }
    }
}

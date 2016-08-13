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

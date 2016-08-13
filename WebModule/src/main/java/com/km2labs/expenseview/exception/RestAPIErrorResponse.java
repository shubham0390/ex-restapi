package com.km2labs.expenseview.exception;

import com.google.common.collect.Maps;
import com.km2labs.expenseview.rest.ResponseGenrator;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;


public class RestAPIErrorResponse {

    private String errorMessage;
    private int httpErrorStatus;
    private String rootCause;
    private Map<String, String> errorContext;

    public RestAPIErrorResponse(String error, int httpErrorStatus) {
        this(error, httpErrorStatus, new HashMap<String, String>());
    }

    public RestAPIErrorResponse(String error, int httpErrorStatus, Map<String, String> errorContext) {
        this.errorMessage = error;
        this.httpErrorStatus = httpErrorStatus;
        this.errorContext = errorContext;
    }

    public String getError() {
        return errorMessage;
    }

    public int getHttpErrorStatus() {
        return httpErrorStatus;
    }

    public void setRootCause(String rootCause) {
        this.rootCause = rootCause;
    }

    public String toJSON() {
        Map<String, Object> errorData = Maps.newHashMap();
        errorData.put("message", errorMessage);
        errorData.put("data", errorContext);
        if (StringUtils.isNotBlank(rootCause)) {
            errorData.put("rootCause", rootCause);
        }
        return ResponseGenrator.toJson(errorData);
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "error='" + errorMessage + '\'' +
                ", httpErrorStatus=" + httpErrorStatus +
                '}';
    }
}

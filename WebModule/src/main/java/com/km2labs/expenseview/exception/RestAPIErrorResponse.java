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

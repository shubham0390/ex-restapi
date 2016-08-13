package com.km2labs.expenseview.rest;

import com.km2labs.expenseview.exception.ExceptionToResponseTranslator;
import com.km2labs.expenseview.exception.RestAPIErrorResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by suze on 11/08/16.
 */
public class RestAPIUtil {

    public static Response buildErrorResponse(Exception ex) {
        RestAPIErrorResponse errorResponse = ExceptionToResponseTranslator.getInstance().getResponseForException(ex);
        return Response.ok(errorResponse, MediaType.APPLICATION_JSON_TYPE).status(errorResponse.getHttpErrorStatus()).build();
    }
}

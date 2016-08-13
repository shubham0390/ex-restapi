package com.km2labs.expenseview.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.util.PatternMatchUtils;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExceptionToResponseTranslator {

    private static final ExceptionToResponseTranslator EXCEPTION_TO_RESPONSE_TRANSLATOR = new ExceptionToResponseTranslator();
    private static Map<String, Response.Status> mExceptionVsCodeLookup;

    public ExceptionToResponseTranslator() {
        if (mExceptionVsCodeLookup == null) {
            mExceptionVsCodeLookup = new HashMap<>();
            mExceptionVsCodeLookup.put("DuplicateEntityException", Response.Status.CONFLICT);
            mExceptionVsCodeLookup.put("AuthenticationException", Response.Status.UNAUTHORIZED);
            mExceptionVsCodeLookup.put("Km2LabsAccessDeniedException", Response.Status.FORBIDDEN);
            mExceptionVsCodeLookup.put("DataException", Response.Status.BAD_REQUEST);
            mExceptionVsCodeLookup.put("JsonProcessingException", Response.Status.BAD_REQUEST);
            mExceptionVsCodeLookup.put("WebApplicationException", Response.Status.INTERNAL_SERVER_ERROR);
            mExceptionVsCodeLookup.put("OperationNotSupportedException", Response.Status.NOT_IMPLEMENTED);
            mExceptionVsCodeLookup.put("IllegalArgumentException", Response.Status.BAD_REQUEST);
            mExceptionVsCodeLookup.put("JsonParseException", Response.Status.INTERNAL_SERVER_ERROR);
            mExceptionVsCodeLookup.put("UnrecognizedPropertyException", Response.Status.BAD_REQUEST);
            mExceptionVsCodeLookup.put("UserNotPresentException", Response.Status.NOT_FOUND);
        }
    }


    public static ExceptionToResponseTranslator getInstance() {
        return EXCEPTION_TO_RESPONSE_TRANSLATOR;
    }

    public RestAPIErrorResponse getResponseForException(Throwable ex) {
        Response.Status statusCode = getExceptionHttpStatusCode(ex);
        return generateErrorResult(statusCode.getStatusCode(), ex);
    }


    protected RestAPIErrorResponse generateErrorResult(int responseCode, Throwable ex) {
        RestAPIErrorResponse errorResponse = null;
        String errorMessage;
        Throwable cause = ex.getCause();
        if (ex instanceof JsonProcessingException || cause instanceof WebApplicationException) {
            errorMessage = "Invalid request/response. Please consult documentation for proper input/output format";
            errorResponse = new RestAPIErrorResponse(errorMessage, responseCode);
        } else {
            errorMessage = ex.getMessage();
            errorResponse = new RestAPIErrorResponse(errorMessage, responseCode);
        }
        return errorResponse;
    }


    private Response.Status getExceptionHttpStatusCode(Throwable pException) {
        // look for the direct match
        Response.Status bestMatchedStatusCode = mExceptionVsCodeLookup.get(pException.getClass().getSimpleName());
        // Look for most specific name match.
        if (bestMatchedStatusCode == null) {
            for (String mappedName : mExceptionVsCodeLookup.keySet()) {
                if (PatternMatchUtils.simpleMatch(mappedName, pException.getClass().getSimpleName())) {
                    bestMatchedStatusCode = mExceptionVsCodeLookup.get(mappedName);
                }
            }
        }
        // means that we could not find the status code, lets check if the
        // exceptions super class is ApplicationException
        // lets fall back on Exception
        if (bestMatchedStatusCode == null) {
            bestMatchedStatusCode = mExceptionVsCodeLookup.get(Exception.class.getSimpleName());
        }
        return bestMatchedStatusCode;
    }

    public static boolean isCausedByNetworkIssue(Throwable throwable) {
        Throwable rootCause = throwable;
        while (rootCause.getCause() != null) {
            rootCause = rootCause.getCause();
            if (rootCause instanceof IOException) {
                return true;
            }
        }
        return rootCause instanceof IOException;
    }
}
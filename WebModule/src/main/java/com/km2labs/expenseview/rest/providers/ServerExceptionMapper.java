package com.km2labs.expenseview.rest.providers;

import com.km2labs.expenseview.rest.RestAPIUtil;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Created by Subham Tyagi
 * On 8/7/2015.
 * <p>
 * TODO: Add class comments
 */
@Provider
@Slf4j
public class ServerExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        return RestAPIUtil.buildErrorResponse(exception);
    }
}

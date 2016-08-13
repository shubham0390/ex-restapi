package com.km2labs.expenseview.rest.providers;

import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;

/**
 * Created by suze on 11/08/16.
 */
@Component
public class ClientExceptionDeligator {
    public Response getResponse(final Exception exception) {
        return null;
    }
}

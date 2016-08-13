package com.km2labs.expenseview.rest.providers;

import org.springframework.stereotype.Component;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 * Created by Subham Tyagi
 * On 10/8/2015.
 * <p>
 * TODO: Add class comments
 */
@Component
public class ExceptionMapperDeligator {

    @Context
    private HttpHeaders httpHeaders;

    public Response deligateException(Exception exception) {

        if (exception.getClass().equals(com.fasterxml.jackson.core.JsonParseException.class)) {
            return Response.status(400)
                    .entity("Invalid Input")
                    .type(httpHeaders.getMediaType())
                    .build();
        } else if (exception.getClass().equals(NotFoundException.class)) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Resource Not Found")
                    .type(httpHeaders.getMediaType())
                    .build();
        } else if (exception.getClass().equals(BadRequestException.class)) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Invalid Request")
                    .type(httpHeaders.getMediaType())
                    .build();
        } else {
            return Response
                    .status(500)
                    .entity("Internal Server error")
                    .build();
        }
    }
}

package com.mmt.shubh.rest.providers;

import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;

/**
 * Created by Subham Tyagi
 * On 10/8/2015.
 * <p>
 * TODO: Add class comments
 */
@Component
public class ExceptionMapperDeligator {

    public Response deligateException(Exception e) {
        Class aClass = e.getClass();
        return Response
                .status(500)
                .entity("Internal Server error")
                .build();
    }
}

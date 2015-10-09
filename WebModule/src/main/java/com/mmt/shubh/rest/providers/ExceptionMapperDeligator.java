package com.mmt.shubh.rest.providers;

import com.mmt.shubh.exception.DuplicateEntityException;
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
        if (aClass.equals(DuplicateEntityException.class) || aClass.equals(IllegalArgumentException.class)) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        } else {
            return Response
                    .status(500)
                    .entity("Internal Server error")
                    .build();
        }
    }
}

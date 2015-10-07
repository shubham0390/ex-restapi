package com.mmt.shubh.rest;

import com.mmt.shubh.rest.response.ServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.jaxrs.impl.ResponseBuilderImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

/**
 * Created by Subham Tyagi
 * On 7/26/2015.
 * <p>
 * TODO: Add class comments
 */
@Slf4j
@Scope(value = "prototype")
@Component
public class ServiceResponseConverter {

    public Response toRestResponse(ServiceResponse response) {
        Response.ResponseBuilder builder = new ResponseBuilderImpl();
        builder.status(getStatus(response.getStatus()));
        builder.entity(response.getPayload());
        return builder.build();
    }


    private Response.Status getStatus(int code) {
        return Response.Status.OK;
    }
}

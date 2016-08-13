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

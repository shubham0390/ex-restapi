package com.mmt.shubh.rest.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Subham Tyagi
 * On 3/22/2015.
 * <p/>
 * TODO: Add class comments
 */

@Data
@AllArgsConstructor
@XmlRootElement
public class ErrorReason {
    private Response.Status statusCode;
    private String value;
}

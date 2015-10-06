package com.mmt.shubh.rest.response;

/**
 * Created by Subham Tyagi
 * On 3/22/2015.
 * <p>
 * TODO: Add class comments
 */

public class SuccessResponse extends ServiceResponse {

    public SuccessResponse() {
        setSucsess(true);
        setStatus(200);
    }
}

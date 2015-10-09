package com.mmt.shubh.rest.response;

/**
 * Created by Subham Tyagi
 * On 3/22/2015.
 * <p>
 * TODO: Add class comments
 */

public class SuccessResponse<T> extends ServiceResponse<T> {

    public SuccessResponse() {
        setSuccess(true);
        setStatus(200);
    }
}

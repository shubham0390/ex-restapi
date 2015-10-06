package com.mmt.shubh.exception;

/**
 * Created by Subham Tyagi
 * On 6/6/2015.
 * <p>
 * TODO: Add class comments
 */
public class DuplicateEntityException extends RuntimeException {

    public DuplicateEntityException() {
    }

    public DuplicateEntityException(String message) {
        super(message);
    }

    public DuplicateEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateEntityException(Throwable cause) {
        super(cause);
    }

    public DuplicateEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

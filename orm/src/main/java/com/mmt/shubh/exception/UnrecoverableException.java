package com.mmt.shubh.exception;

/**
 * Created by Subham Tyagi
 * On 8/8/2015.
 * <p>
 * TODO: Add class comments
 */
public class UnrecoverableException extends RuntimeException {

    public UnrecoverableException() {
    }

    public UnrecoverableException(String message) {
        super(message);
    }

    public UnrecoverableException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnrecoverableException(Throwable cause) {
        super(cause);
    }

    public UnrecoverableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

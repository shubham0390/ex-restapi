package com.mmt.shubh.exception;

/**
 * Created by Subham Tyagi
 * On 8/8/2015.
 * <p>
 * TODO: Add class comments
 */
public class InvalidEntityException extends RuntimeException {
    public InvalidEntityException() {
    }

    public InvalidEntityException(String message) {
        super(message);
    }

    public InvalidEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEntityException(Throwable cause) {
        super(cause);
    }

    public InvalidEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

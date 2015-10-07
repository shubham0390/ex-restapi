package com.mmt.shubh.exception;

/**
 * Created by Subham Tyagi
 * On 6/6/2015.
 * <p>
 * TODO: Add class comments
 */
public class EntityNotFountException extends RuntimeException {

    public EntityNotFountException() {
    }

    public EntityNotFountException(String message) {
        super(message);
    }

    public EntityNotFountException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFountException(Throwable cause) {
        super(cause);
    }

    public EntityNotFountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

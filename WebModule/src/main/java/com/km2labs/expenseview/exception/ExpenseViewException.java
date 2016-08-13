package com.km2labs.expenseview.exception;


/**
 * Created by suze on 07/08/16.
 */

public abstract class ExpenseViewException extends RuntimeException {

    private Object message;

    public ExpenseViewException(Object message) {
        super(message.toString());
    }
}

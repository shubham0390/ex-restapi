package com.km2labs.expenseview.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * User: vedsurtani
 * Date: 02/01/14
 * Time: 8:17 PM
 */
public class ResponseExclusionStrategy implements ExclusionStrategy {
    private static final ResponseExclusionStrategy INSTANCE = new ResponseExclusionStrategy();

    public static ResponseExclusionStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return (fieldAttributes.getAnnotation(JsonIgnore.class) != null || (fieldAttributes.getAnnotation(FieldIgnore.class) != null));
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}

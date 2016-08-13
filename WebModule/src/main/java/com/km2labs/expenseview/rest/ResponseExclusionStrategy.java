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

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

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import org.springframework.beans.BeanUtils;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by suze on 11/08/16.
 */
public class ResponseGenrator {

    private static CacheControl getCacheControl() {
        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(0);
        cacheControl.setNoCache(true);
        return cacheControl;
    }

    /**
     * Defaults to application/json media type.
     *
     * @param o object to be used for Response.
     * @return The {@link Response} object.
     */
    public static Response generateResponseWithTimestampDates(Object o) {
        return generateResponseWithTimestampDates(o, MediaType.APPLICATION_JSON_TYPE, false);
    }

    /**
     * @param o                   object to be used for Response.
     * @param mediaType           The {@link MediaType} to use.
     * @param disableHtmlEscaping if set to true, then response does not have escaped html. This is useful for mobile apps which do no have
     *                            jsonEval available.
     */
    private static Response generateResponseWithTimestampDates(final Object o, final MediaType mediaType, final boolean disableHtmlEscaping) {

        if (o != null) {
            if (BeanUtils.isSimpleValueType(o.getClass())) {
                GsonBuilder builder = getDefaultGsonBuilder();
                if (disableHtmlEscaping) {
                    builder.disableHtmlEscaping();
                }
                builder.registerTypeAdapter(Date.class, new DateSerializer());
                Gson gson = builder.create();
                return Response.ok(gson.toJson(o), mediaType).cacheControl(getCacheControl()).build();

            } else {
                return generateStreamingResponse(o, disableHtmlEscaping, true);
            }

        } else {
            return Response.noContent().build();
        }
    }

    public static Response generateResponse(Object o) {
        return generateResponse(o, false);
    }

    /**
     * This method is same as @ref generateResponse(Object) except that it disables html escaping
     */
    public static Response generateAPIRespone(final Object o) {
        return generateResponse(o, true);
    }

    private static Response generateResponse(Object o, boolean disableHtmlEscaping) {
        if (o != null) {
            if (BeanUtils.isSimpleValueType(o.getClass())) {
                GsonBuilder defaultGsonBuilder = getDefaultGsonBuilder();
                if (disableHtmlEscaping) {
                    defaultGsonBuilder.disableHtmlEscaping();
                }
                return Response.ok(defaultGsonBuilder.create().toJson(o), MediaType.APPLICATION_JSON_TYPE).cacheControl(getCacheControl()).build();
            }
            return generateStreamingResponse(o, disableHtmlEscaping);
        } else {
            return Response.noContent().build();
        }
    }

    private static class DateSerializer implements JsonSerializer<Date> {
        @Override
        public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
            return date == null ? null : new JsonPrimitive(date.getTime());
        }
    }

    private static Response generateStreamingResponse(final Object o, final boolean disableHtmlEscaping) {
        return generateStreamingResponse(o, disableHtmlEscaping, false);
    }

    private static Response generateStreamingResponse(final Object o, final boolean disableHtmlEscaping, final boolean dateTimeStamp) {
        return generateStreamingResponse(o, disableHtmlEscaping, dateTimeStamp, MediaType.APPLICATION_JSON_TYPE);
    }

    private static Response generateStreamingResponse(final Object o, final boolean disableHtmlEscaping, final boolean dateTimeStamp,
                                                      MediaType mediaType) {
        if (o != null) {
            StreamingOutput stream = outputStream -> {
                GsonBuilder builder = getDefaultGsonBuilder();
                if (disableHtmlEscaping) {
                    builder.disableHtmlEscaping();
                }
                if (dateTimeStamp) {
                    builder.registerTypeAdapter(Date.class, new DateSerializer());
                }
                Gson gson = builder.create();
                JsonWriter writer = new JsonWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                gson.toJson(o, o.getClass(), writer);
                writer.flush();
            };
            return Response.ok(stream, mediaType).cacheControl(getCacheControl()).build();
        } else {
            return Response.noContent().build();
        }
    }

    private static GsonBuilder getDefaultGsonBuilder() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.serializeSpecialFloatingPointValues();
        builder.addSerializationExclusionStrategy(ResponseExclusionStrategy.getInstance());
        return builder;
    }

    public static String toJson(Object o) {
        GsonBuilder builder = getDefaultGsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(o);
    }


}

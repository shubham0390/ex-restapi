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

package com.km2labs.expenseview.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by suze on 07/08/16.
 */
@Getter
@Setter
public class DataException extends ClientException {

    public DataException(List<DataError> message) {
        super(message);
    }

    @Getter
    @Setter
    public static class DataError {
        private int code;
        private String message;

        public DataError(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}

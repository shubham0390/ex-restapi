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

/**
 * Created by suze on 11/08/16.
 */
public interface ErrorCodes {
    int MISSING_USER = 100;
    int MISSING_USER_NAME = 101;
    int MISSING_PHONE_NUMBER = 102;
    int MISSING_DEVICE = 103;
    int MISSING_DEVICE_UUID = 104;
    int MISSING_GCM_TOKEN = 105;
}

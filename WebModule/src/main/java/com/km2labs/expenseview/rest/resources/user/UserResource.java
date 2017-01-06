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

package com.km2labs.expenseview.rest.resources.user;

import com.km2labs.expenseview.rest.dto.Device;
import com.km2labs.expenseview.rest.dto.auth.LoginRequestDto;
import com.km2labs.expenseview.service.LoginType;
import com.km2labs.expenseview.service.device.DeviceService;
import com.km2labs.expenseview.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
import java.util.Collection;

import static com.km2labs.expenseview.rest.ResponseGenrator.generateResponseWithTimestampDates;
import static com.km2labs.expenseview.rest.RestAPIUtil.buildErrorResponse;

@Component
public class UserResource implements IUserResources {

    private final IUserService mUserService;

    private final DeviceService mDeviceService;

    @Autowired
    public UserResource(@Qualifier(value = "userService") IUserService mUserService,
                        @Qualifier("deviceServiceImpl") DeviceService mDeviceService) {
        this.mUserService = mUserService;
        this.mDeviceService = mDeviceService;
    }

    @Override
    public long addDevice(@NotNull long userID, Device device) {
        return mDeviceService.addDevice(userID, device).getId();
    }

    @Override
    public Response updateGCMTokenDevice(@NotNull final String userId, final String deviceId, final String GCMToken) {
        try {
            Device device = mDeviceService.updateGcmToken(GCMToken, deviceId, userId);
            return generateResponseWithTimestampDates(device);
        } catch (Exception e) {
            return buildErrorResponse(e);
        }
    }

    @Override
    public Response deleteDevice(String deviceId, String userId) {
        try {
            String result = mDeviceService.deleteDevice(deviceId, userId);
            return generateResponseWithTimestampDates(result);
        } catch (Exception e) {
            return buildErrorResponse(e);
        }
    }

    @Override
    public Response getDevices(@NotNull String userId) {
        try {
            Collection<Device> devices = mDeviceService.getDevicesByUser(userId);
            return generateResponseWithTimestampDates(devices);
        } catch (Exception e) {
            return buildErrorResponse(e);
        }
    }

    @Override
    public LoginRequestDto getDemo() {
        LoginRequestDto signupRequest = new LoginRequestDto();
        Device device = new Device();
        device.setDeviceUUID("afdkasdfkhaskfdhaskdfhfsh");
        device.setGcmToken("ahfdkjhsadjfahsdj");
        signupRequest.setDevice(device);
        return signupRequest;
    }


}

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

import com.km2labs.expenseview.rest.model.Device;
import com.km2labs.expenseview.rest.model.User;
import com.km2labs.expenseview.service.LoginType;
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

    @Autowired
    public UserResource(@Qualifier(value = "userService") IUserService mUserService) {
        this.mUserService = mUserService;
    }

    @Override
    public Response signup(SignupRequest signupRequest) {
        User inputUser = signupRequest.getUser();
        SignupResponse signupResponse = new SignupResponse();
        try {
            User user = mUserService.signup(signupRequest.getLoginType(), signupRequest.getAuthenticationToken(), inputUser);
            signupResponse.setUser(user);
            return generateResponseWithTimestampDates(signupResponse);

        } catch (Exception e) {
            return buildErrorResponse(e);
        }
    }

    @Override
    public Response login(LoginRequest loginRequest) {
        try {
            User user = mUserService.user(loginRequest.getLoginType(), loginRequest.getAuthenticationToken(), loginRequest.getMobileNo(), loginRequest.getDevice());
            return generateResponseWithTimestampDates(new LoginResponse(user));
        } catch (Exception e) {
            return buildErrorResponse(e);
        }
    }

    @Override
    public void logout(String emailId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long addDevice(@NotNull long userID, Device device) {
        return mUserService.addDevice(userID, device);
    }

    @Override
    public Response updateGCMTokenDevice(@NotNull final String userId, final String deviceId, final String GCMToken) {
        try {
            Device device = mUserService.updateGcmToken(GCMToken, deviceId, userId);
            return generateResponseWithTimestampDates(device);
        } catch (Exception e) {
            return buildErrorResponse(e);
        }
    }

    @Override
    public Response deleteDevice(String deviceId, String userId) {
        try {
            String result = mUserService.deleteDevice(deviceId, userId);
            return generateResponseWithTimestampDates(result);
        } catch (Exception e) {
            return buildErrorResponse(e);
        }
    }

    @Override
    public Response getDevices(@NotNull String userId) {
        try {
            Collection<Device> devices = mUserService.getDevicesByUser(userId);
            return generateResponseWithTimestampDates(devices);
        } catch (Exception e) {
            return buildErrorResponse(e);
        }
    }

    @Override
    public LoginRequest getDemo() {
        LoginRequest signupRequest = new LoginRequest();
        Device device = new Device();
        device.setDeviceUUID("afdkasdfkhaskfdhaskdfhfsh");
        device.setGcmToken("ahfdkjhsadjfahsdj");
        signupRequest.setDevice(device);
        signupRequest.setAuthenticationToken("asjdfkjshdflkjhlqwuehihalsdkjhlkfjhkl");
        signupRequest.setLoginType(LoginType.GOOGLE.name());
        signupRequest.setMobileNo("9663295153");
        return signupRequest;
    }


}

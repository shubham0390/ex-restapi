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

import com.km2labs.expenseview.rest.ResponseGenrator;
import com.km2labs.expenseview.rest.RestAPIUtil;
import com.km2labs.expenseview.rest.model.Device;
import com.km2labs.expenseview.rest.model.User;
import com.km2labs.expenseview.service.LoginType;
import com.km2labs.expenseview.service.device.IDeviceService;
import com.km2labs.expenseview.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserResource implements IUserResources {

    private final IDeviceService mDeviceService;

    private final IUserService mUserService;

    @Autowired
    public UserResource(@Qualifier(value = "deviceServiceImpl") IDeviceService mDeviceService, @Qualifier(value = "userService") IUserService mUserService) {
        this.mDeviceService = mDeviceService;
        this.mUserService = mUserService;
    }

    @Override
    public Response signup(SignupRequest signupRequest) {
        User inputUser = signupRequest.getUser();
        SignupResponse signupResponse = new SignupResponse();
        try {
            User user = mUserService.signup(signupRequest.getLoginType(), signupRequest.getAuthenticationToken(), inputUser);
            signupResponse.setUser(user);
            return ResponseGenrator.generateResponseWithTimestampDates(signupResponse);

        } catch (Exception e) {
            return RestAPIUtil.buildErrorResponse(e);
        }
    }

    @Override
    public Response login(LoginRequest loginRequest) {
        try {
            long deviceId = mUserService.login(loginRequest.getLoginType(), loginRequest.getAuthenticationToken(), loginRequest.getMobileNo(), loginRequest.getDevice());
            return ResponseGenrator.generateResponseWithTimestampDates(new LoginResponse(deviceId));
        } catch (Exception e) {
            return RestAPIUtil.buildErrorResponse(e);
        }
    }

    @Override
    public void logout(String emailId) {
        // mMemberService.logout(emailId);
    }

    @Override
    public long addDevice(@NotNull long memberId, Device device) {
        return mDeviceService.addDevice(memberId, device).getId();
    }


    @Override
    public Device updateDevice(String emailId, Device device) {
        return mDeviceService.updateDevice(device, emailId);
    }


    @Override
    public String deleteDevice(String deviceUUID, String emailId) {
        return mDeviceService.deleteDevice(deviceUUID, emailId);
    }

    @Override
    public User getDevices(@NotNull String memberId) {
        User user = new User();
        user.setEmail("shubham.k.tyagi@gmail.com");
        user.setName("Subham Tyagi");
        user.setPhoneNumber("9663295153");
        user.setProfileImageUrl("ajsbdlfkjasljdkfhalsjkhdfljs");
        user.setCoverImageUrl("mdnckajsndkjfskdjnfkjd");
        Set<Device> mDeviceDetailses = new HashSet<>();
        Device device = new Device();
        device.setDeviceUUID("afdkasdfkhaskfdhaskdfhfsh");
        device.setGcmToken("ahfdkjhsadjfahsdj");
        device.setId(123456789);
        mDeviceDetailses.add(device);
        user.setDevice(device);

        return user;
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

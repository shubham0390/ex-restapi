package com.mmt.shubh.rest.resources.user;

import com.mmt.shubh.rest.ApiResult;
import com.mmt.shubh.rest.model.Device;
import com.mmt.shubh.rest.model.result.MemberRegistrationResult;
import com.mmt.shubh.service.device.IDeviceService;
import com.mmt.shubh.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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
    public ApiResult<MemberRegistrationResult> signup(SignupRequest signupRequest) {
        mUserService.signup(signupRequest.getUser(), signupRequest.getDevice());
        return null;
    }

    @Override
    public ApiResult<MemberRegistrationResult> login(String phoneNumber) {
        return null;
    }

    @Override
    public void logout(String emailId) {
        // mMemberService.logout(emailId);
    }

    @Override
    public ApiResult<MemberRegistrationResult> generateOTP(long memberId) {
        return null;
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
    public List<Device> getMemberDevices(@NotNull long memberId) {
        List<Device> mDeviceDetailses = new ArrayList<>();
        Device device = new Device();
        device.setDeviceUUID("afdkasdfkhaskfdhaskdfhfsh");
        device.setGcmToken("ahfdkjhsadjfahsdj");
        device.setId(123456789);
        mDeviceDetailses.add(device);
        return mDeviceDetailses;
    }


}

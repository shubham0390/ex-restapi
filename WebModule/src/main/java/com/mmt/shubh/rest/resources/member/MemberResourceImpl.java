package com.mmt.shubh.rest.resources.member;

import com.mmt.shubh.rest.ApiResult;
import com.mmt.shubh.rest.model.DeviceDetails;
import com.mmt.shubh.rest.model.LoginStatus;
import com.mmt.shubh.rest.model.Member;
import com.mmt.shubh.rest.model.result.MemberRegistrationResult;
import com.mmt.shubh.service.device.IDeviceService;
import com.mmt.shubh.service.member.IMemberService;
import com.mmt.shubh.service.member.MemberServiceImpl;
import com.mmt.shubh.utility.MemberState;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
@Slf4j
@Service
public class MemberResourceImpl implements IMemberResource {

    @Qualifier(value = "memberServiceImpl")
    @Autowired()
    private IMemberService mMemberService = new MemberServiceImpl();

    @Qualifier(value = "deviceServiceImpl")
    @Autowired
    private IDeviceService mDeviceService;

    @Override
    public ApiResult<MemberRegistrationResult> registerMember(Member member) {
        mMemberService.registerMember(member);
        return null;
    }

    @Override
    public ApiResult<MemberRegistrationResult> login(String phoneNumber) {
        return null;
    }

    @Override
    public Member updateMember(Member member) {
        return mMemberService.updateMember(member);
    }

    @Override
    public String updateGCMToken(String GCMToken, String emailId) {
        return mDeviceService.updateGCMToken(GCMToken, emailId);
    }

    @Override
    public long addDevice(@NotNull long memberId, DeviceDetails deviceDetails) {
        return mDeviceService.addDevice(memberId, deviceDetails);
    }

    @Override
    public Member getMember(String emailId) {
        return mMemberService.getMember(emailId);
    }

    @Override
    public DeviceDetails updateDevice(String emailId, DeviceDetails deviceDetails) {
        return mDeviceService.updateDevice(deviceDetails, emailId);
    }


    @Override
    public String deleteDevice(String deviceUUID, String emailId) {
        return mDeviceService.deleteDevice(deviceUUID, emailId);
    }

    @Override
    public List<DeviceDetails> getMemberDevices(@NotNull long memberId) {
        List<DeviceDetails> mDeviceDetailses = new ArrayList<>();
        DeviceDetails deviceDetails = new DeviceDetails();
        deviceDetails.setDeviceUUID("afdkasdfkhaskfdhaskdfhfsh");
        deviceDetails.setGcmToken("ahfdkjhsadjfahsdj");
        deviceDetails.setId(123456789);
        mDeviceDetailses.add(deviceDetails);
        return mDeviceDetailses;
    }

    @Override
    public String deleteMember(String emailId) {
        return mMemberService.deleteMember(emailId);
    }

    @Override
    public List<Member> getExpenseBookMembers(long expenseBookId) {
        return mMemberService.getExpenseBookMember(expenseBookId);
    }

    @Override
    public Response syncMember(long expenseBookId, long syncId) {
        return null;
    }

    @Override
    public String getDemo() {
        return "Hello how are you";
    }

    public ApiResult<LoginStatus> login(Member member) {
        String accessToken = mMemberService.login(member);
        ApiResult<LoginStatus> apiResult = new ApiResult<>();

        LoginStatus loginStatus = new LoginStatus();

        if (TextUtils.isEmpty(accessToken)) {
            loginStatus.setAccessToken(accessToken);
            loginStatus.setLoginStatus(MemberState.LOGGED_IN.name());
            apiResult.setStatus(true);
        } else {
            apiResult.setStatus(true);
        }
        apiResult.setData(loginStatus);
        return apiResult;
    }

    @Override
    public void logout(String emailId) {
        mMemberService.logout(emailId);
    }
}

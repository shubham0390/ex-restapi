package com.mmt.shubh.rest.resources.member;

import com.mmt.shubh.rest.model.DeviceDetails;
import com.mmt.shubh.rest.model.Member;
import com.mmt.shubh.service.device.IDeviceService;
import com.mmt.shubh.service.member.IMemberService;
import com.mmt.shubh.service.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
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

    @Qualifier("memberServiceImpl")
    @Autowired(required = true)
    private IMemberService mMemberService = new MemberServiceImpl();

    @Qualifier("deviceServiceImpl")
    @Autowired
    private IDeviceService mDeviceService;

    @Override
    public Member registerMember(Member member) {
        return mMemberService.registerMember(member);
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
    public DeviceDetails addDevice(@NotNull @Email String emailId, DeviceDetails deviceDetails) {
        return mDeviceService.addDevice(emailId, deviceDetails);
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
}

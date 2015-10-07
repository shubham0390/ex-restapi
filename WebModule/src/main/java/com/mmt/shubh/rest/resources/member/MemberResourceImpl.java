package com.mmt.shubh.rest.resources.member;

import com.mmt.shubh.rest.ServiceResponseConverter;
import com.mmt.shubh.rest.model.DeviceDetails;
import com.mmt.shubh.rest.model.Member;
import com.mmt.shubh.rest.response.ServiceResponse;
import com.mmt.shubh.service.device.IDeviceService;
import com.mmt.shubh.service.member.IMemberService;
import com.mmt.shubh.service.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
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
    private IMemberService mMemberService =  new MemberServiceImpl();

    @Autowired
    private ServiceResponseConverter mResponseConverter;

    @Qualifier("deviceServiceImpl")
    @Autowired
    private IDeviceService mDeviceService;

    @Context
    HttpHeaders mHttpHeaders;

    @Override
    public Response registerMember(Member member) {
        ServiceResponse response = mMemberService.registerMember(member);
        return mResponseConverter.toRestResponse(response);
        //return null;
    }

    @Override
    public Response updateMember(Member member) {
        return null;
    }


    @Override
    public Response updateGCMToken(String GCMToken, String emailId) {
        return null;
    }

    @Override
    public Member getMember(String emailId) {
        return new Member();
    }

    @Override
    public Response getMembers(long expenseBookId) {
        return null;
    }

    @Override
    public Response updateDevice(String emailId, DeviceDetails deviceDetails) {
        /*ServiceResponse response = mDeviceService.updateDevice(httpHeaders, emailId);
        return mResponseConverter.toRestResponse(response);*/
        return null;
    }


    @Override
    public Response deleteDevice(String detailsUUID, String emailId) {
        ServiceResponse response = mDeviceService.deleteDevice(detailsUUID, emailId);
        return mResponseConverter.toRestResponse(response);
    }

    @Override
    public Response deleteMember(String emailId) {
        ServiceResponse response = mMemberService.deleteMember(emailId);
        return mResponseConverter.toRestResponse(response);
    }

    @Override
    public List<Member> getExpenseBookMembers(long expenseBookId) {
        return null;
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

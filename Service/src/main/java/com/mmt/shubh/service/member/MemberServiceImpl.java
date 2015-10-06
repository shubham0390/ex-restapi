package com.mmt.shubh.service.member;

import com.mmt.shubh.entity.MemberEntity;
import com.mmt.shubh.exception.DuplicateEntityException;
import com.mmt.shubh.exception.InvalidEntityException;
import com.mmt.shubh.exception.UnrecoverableException;
import com.mmt.shubh.repository.member.IMemberRepository;
import com.mmt.shubh.rest.model.DeviceDetails;
import com.mmt.shubh.rest.model.Member;
import com.mmt.shubh.rest.response.ServiceResponse;
import com.mmt.shubh.rest.response.SuccessResponse;
import com.mmt.shubh.service.converter.EntityModelConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
@Component(value = "memberServiceImpl")
@Slf4j
@Transactional
public class MemberServiceImpl implements IMemberService {

    @Qualifier("memberRepositoryImpl")
    @Autowired
    IMemberRepository mMemberRepository;

    @Qualifier("memberEntityModelConverter")
    @Autowired
    EntityModelConverter<MemberEntity, Member> mEntityModelConverter;

    public ServiceResponse updateMember(Member member) {
        ServiceResponse serviceResponse = null;
        try {
            mMemberRepository.updateMember(mEntityModelConverter.toEntity(member));
            serviceResponse = new SuccessResponse();
        } catch (Exception e) {

        }
        return serviceResponse;
    }

    public ServiceResponse deleteMember(long id) {
        return null;
    }

    public ServiceResponse updateGCMToken(DeviceDetails deviceDetails, String emailId) {
        return null;
    }

    public ServiceResponse getMember(String emailId) {
        return null;
    }

    public ServiceResponse registerMember(Member member) {
        ServiceResponse serviceResponse = null;
        try {
            mMemberRepository.registerMember(mEntityModelConverter.toEntity(member));
            serviceResponse = new SuccessResponse();
            serviceResponse.setPayload("Member registered successfully with emailId" + member.getMemberEmail());
        } catch (DuplicateEntityException e) {
            e.printStackTrace();
        } catch (InvalidEntityException e) {
            e.printStackTrace();
        } catch (UnrecoverableException e) {
            e.printStackTrace();
        }
        return serviceResponse;
    }

    public ServiceResponse updateDevice(DeviceDetails deviceDetails, String emailId) {
        return null;
    }

    public ServiceResponse deleteDevice(DeviceDetails details, String emailId) {
        return null;
    }

    public ServiceResponse deleteMember(String emailId) {
        return null;
    }
}

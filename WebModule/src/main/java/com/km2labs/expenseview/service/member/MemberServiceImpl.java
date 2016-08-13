package com.km2labs.expenseview.service.member;

import com.km2labs.expenseview.database.entity.MemberEntity;
import com.km2labs.expenseview.database.repository.member.IMemberRepository;
import com.km2labs.expenseview.rest.model.Device;
import com.km2labs.expenseview.rest.model.Member;
import com.km2labs.expenseview.service.converter.IEntityModelConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

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

    private final IMemberRepository mMemberRepository;

    private final IEntityModelConverter<MemberEntity, Member> mIEntityModelConverter;

    @Autowired
    public MemberServiceImpl(@Qualifier(value = "memberSQLRepository") IMemberRepository mMemberRepository, @Qualifier(value = "memberEntityModelConverter") IEntityModelConverter<MemberEntity, Member> mIEntityModelConverter) {
        this.mMemberRepository = mMemberRepository;
        this.mIEntityModelConverter = mIEntityModelConverter;
    }

    public Member updateMember(Member member) {
        MemberEntity memberEntity = mMemberRepository.updateMember(mIEntityModelConverter.toEntity(member));
        return mIEntityModelConverter.toModel(memberEntity);
    }

    public long deleteMember(long id) {
        mMemberRepository.deleteMember(id);
        return 0;
    }

    public Member registerMember(Member member) {
        log.debug("REGISTER MEMBER STARTS");
        Member retMember;
        Device retDeviceDetails;
        MemberEntity memberEntity = null;
        try {
            memberEntity = mMemberRepository.getMemberByEmail(member.getMemberEmail());
        } catch (NoResultException e) {
            log.info("Member is not present");
        } catch (EmptyResultDataAccessException e) {
            log.info("Member is not present");
        }

        if (memberEntity == null) {
            MemberEntity entity = mIEntityModelConverter.toEntity(member);
            entity.setRegistered(true);
            try {
                memberEntity = mMemberRepository.createMember(entity);
                //retDeviceDetails = mDeviceService.addDevice(memberEntity.getId(), member.getDeviceDetailsList());
                //mMemberOTPService.generateOTPService(memberEntity.getId(), retDeviceDetails.getId());
            } catch (DataIntegrityViolationException e) {
                Response.ResponseBuilder builder = Response.status(Response.Status.CONFLICT)
                        .entity("Member already present with following email Address");
                throw new WebApplicationException(builder.build());
            }
        } else {
            if (memberEntity.isRegistered()) {
                log.debug("Member is already registered.");
                Response.ResponseBuilder builder = Response.status(Response.Status.CONFLICT);
                builder.entity("Member already registered. Try to login again");
                throw new WebApplicationException(builder.build());
            } else {
                log.debug("Member is already present but not registered. Generating new otp");
                long memberId = memberEntity.getId();
                /*if (retDeviceDetails == null) {
                    if (member.getDeviceDetailsList() != null) {
                        mDeviceService.addDevice(memberId, member.getDeviceDetailsList());
                    }
                }*/
                Response.ResponseBuilder builder = Response.status(Response.Status.UNAUTHORIZED);
                builder.entity("Member already registered. Try to login again");
                throw new WebApplicationException(builder.build());
            }
        }

        retMember = mIEntityModelConverter.toModel(memberEntity);
        //retMember.setDeviceDetailsList(retDeviceDetails);
        log.debug("REGISTER MEMBER END");
        return retMember;
    }

    public String deleteMember(String emailId) {
        return null;
    }

    @Override
    public List<Member> getExpenseBookMember(long expenseBookId) {
        return (List<Member>) mIEntityModelConverter.toModel(mMemberRepository.getMembersByExpenseBook(expenseBookId));
    }

    @Override
    public List<Member> createMembers(List<Member> members) {
        members.forEach(this::createMember);
        return null;
    }

    @Override
    public Member getMemberByEmail(String memberEmail) {
        MemberEntity memberByEmail = null;
        try {
            memberByEmail = mMemberRepository.getMemberByEmail(memberEmail);
        } catch (NoResultException e) {
            log.info("Member is not present");
            throw new WebApplicationException(Response.status(Response.Status.NO_CONTENT).entity("No member present for" +
                    " following email Id" + memberEmail).build());
        } catch (EmptyResultDataAccessException e) {
            log.info("Member is not present");
            throw new WebApplicationException(Response.status(Response.Status.NO_CONTENT).entity("No member present for" +
                    " following email Id" + memberEmail).build());
        }
        return mIEntityModelConverter.toModel(memberByEmail);
    }

    @Override
    public Member createMember(Member member) {
        MemberEntity memberEntity = mMemberRepository.createMember(mIEntityModelConverter.toEntity(member));
        return mIEntityModelConverter.toModel(memberEntity);
    }

    @Override
    public Member getMemberById(long memberServerId) {
        MemberEntity memberByEmail = null;
        try {
            memberByEmail = mMemberRepository.getMemberById(memberServerId);
        } catch (NoResultException e) {
            log.info("Member is not present");
            throw new WebApplicationException(Response.status(Response.Status.NO_CONTENT).entity("No member present for" +
                    " following  Id" + memberServerId).build());
        } catch (EmptyResultDataAccessException e) {
            log.info("Member is not present");
            throw new WebApplicationException(Response.status(Response.Status.NO_CONTENT).entity("No member present for" +
                    " following  Id" + memberServerId).build());
        }
        return mIEntityModelConverter.toModel(memberByEmail);
    }


}

package com.mmt.shubh.service.member;

import com.mmt.shubh.database.entity.MemberEntity;
import com.mmt.shubh.database.repository.member.IMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Created by Subham on 18/05/16.
 */
@Slf4j
@Component
public class MemberLoginServiceImpl implements IMemberLoginService {

    @Qualifier(value = "memberSQLRepository")
    @Autowired
    IMemberRepository mMemberRepository;

    @Qualifier("memberOTPServiceImpl")
    @Autowired
    IMemberTokenService mMemberOTPService;

    @Override
    public String login(String uniqueId) {
        boolean isRegistered = false;

        MemberEntity memberEntity = null;
        try {
            memberEntity = mMemberRepository.getMemberUniqueId(uniqueId);
        } catch (NoResultException e) {
            log.info("Member is not present");

        } catch (EmptyResultDataAccessException e) {
            log.info("Member is not present");
        }

        if (memberEntity != null) {
            isRegistered = memberEntity.isRegistered();
        } else {
            throw new WebApplicationException(Response.status(Response.Status.NO_CONTENT)
                    .entity("Member is not Registered").build());
        }

        if (!isRegistered) {
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Member is not verified").build());
        }
        String emailId = memberEntity.getMemberEmail();
        return mMemberOTPService.generateAccessToken(emailId);
    }

    @Override
    public void logout(String emailId) {

    }
}

package com.mmt.shubh.service.member;

import com.mmt.shubh.entity.MemberEntity;
import com.mmt.shubh.repository.member.IMemberRepository;
import com.mmt.shubh.rest.model.Member;
import com.mmt.shubh.service.converter.IEntityModelConverter;
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

    @Qualifier("memberRepositoryImpl")
    @Autowired
    IMemberRepository mMemberRepository;

    @Qualifier("memberEntityModelConverter")
    @Autowired
    IEntityModelConverter<MemberEntity, Member> mIEntityModelConverter;

    public Member updateMember(Member member) {
        MemberEntity memberEntity = mMemberRepository.updateMember(mIEntityModelConverter.toEntity(member));
        return mIEntityModelConverter.toModel(memberEntity);
    }

    public long deleteMember(long id) {
        mMemberRepository.deleteMember(id);
        return 0;
    }

    public Member getMember(String emailId) {
        return mIEntityModelConverter.toModel(mMemberRepository.getMemberByEmail(emailId));
    }

    public Member registerMember(Member member) {
        log.debug("REGISTER MEMBER STARTS");
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
                log.debug("Member is already present but not registered. Marking ias registered");
                memberEntity.setRegistered(true);
                mMemberRepository.updateMember(memberEntity);
            }
        }
        log.debug("REGISTER MEMBER END");
        return mIEntityModelConverter.toModel(memberEntity);
    }

    public String deleteMember(String emailId) {
        return null;
    }

    @Override
    public List<Member> getExpenseBookMember(long expenseBookId) {
        return mIEntityModelConverter.toModel(mMemberRepository.getMembersByExpenseBook(expenseBookId));
    }

    @Override
    public List<Member> createMembers(List<Member> members) {
        members.forEach(member -> {
            createMember(member);
        });
        return null;
    }

    @Override
    public Member getMemberByEmail(String memberEmail) {
        return mIEntityModelConverter.toModel(mMemberRepository.getMemberByEmail(memberEmail));
    }

    @Override
    public Member createMember(Member member) {
        MemberEntity memberEntity = mMemberRepository.createMember(mIEntityModelConverter.toEntity(member));
        return mIEntityModelConverter.toModel(memberEntity);
    }
}

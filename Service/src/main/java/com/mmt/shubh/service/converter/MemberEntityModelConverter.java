package com.mmt.shubh.service.converter;

import com.mmt.shubh.entity.MemberEntity;
import com.mmt.shubh.rest.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Subham Tyagi
 * On 8/8/2015.
 * <p>
 * TODO: Add class comments
 */
@Component(value = "memberEntityModelConverter")
@Slf4j
@Scope(value = "singleton")
public class MemberEntityModelConverter implements EntityModelConverter<MemberEntity, Member> {

    public MemberEntity toEntity(Member member) {
        log.info("converting member model to entity ");
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setDisplayName(member.getDisplayName());
        memberEntity.setUserPassword(member.getUserPassword());
        memberEntity.setProfilePhotoUrl(member.getProfilePhotoUrl());
        memberEntity.setMemberName(member.getMemberName());
        memberEntity.setMemberEmail(member.getMemberEmail());
        memberEntity.setCoverPhotoUrl(member.getCoverPhotoUrl());
        return memberEntity;
    }

    public Member toModel(MemberEntity memberEntity) {
        return null;
    }
}

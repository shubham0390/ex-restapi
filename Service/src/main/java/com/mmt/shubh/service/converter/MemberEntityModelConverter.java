package com.mmt.shubh.service.converter;

import com.mmt.shubh.entity.MemberEntity;
import com.mmt.shubh.rest.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Subham Tyagi
 * On 8/8/2015.
 * <p>
 * TODO: Add class comments
 */
@Component(value = "memberEntityModelConverter")
@Slf4j
@Scope(value = "singleton")
public class MemberEntityModelConverter implements IEntityModelConverter<MemberEntity, Member> {

    public MemberEntity toEntity(Member member) {
        log.info("converting memberEntity model to entity ");
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
        Member member = new Member();
        member.setDisplayName(memberEntity.getDisplayName());
        member.setProfilePhotoUrl(memberEntity.getProfilePhotoUrl());
        member.setMemberName(memberEntity.getMemberName());
        member.setMemberEmail(memberEntity.getMemberEmail());
        member.setCoverPhotoUrl(memberEntity.getCoverPhotoUrl());
        return member;
    }

    @Override
    public List<MemberEntity> toEntity(List<Member> m) {
        List<MemberEntity> memberEntities = new ArrayList<>();
        m.forEach(member -> {
            memberEntities.add(toEntity(member));
        });
        return memberEntities;
    }

    @Override
    public List<Member> toModel(List<MemberEntity> e) {
        List<Member> members = new ArrayList<>();
        e.forEach(memberEntity -> {
            members.add(toModel(memberEntity));
        });
        return members;
    }
}

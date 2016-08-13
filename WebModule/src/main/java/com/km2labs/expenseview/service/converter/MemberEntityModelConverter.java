package com.km2labs.expenseview.service.converter;

import com.km2labs.expenseview.database.entity.MemberEntity;
import com.km2labs.expenseview.rest.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by Subham Tyagi
 * On 8/8/2015.
 * <p>
 * TODO: Add class comments
 */

@Component(value = "memberEntityModelConverter")
@Slf4j
public class MemberEntityModelConverter implements IEntityModelConverter<MemberEntity, Member> {

    public MemberEntity toEntity(Member member) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUserPassword(member.getUserPassword());
        memberEntity.setProfilePhotoUrl(member.getProfilePhotoUrl());
        memberEntity.setMemberName(member.getMemberName());
        memberEntity.setMemberEmail(member.getMemberEmail());
        memberEntity.setCoverPhotoUrl(member.getCoverPhotoUrl());
        return memberEntity;
    }

    public Member toModel(MemberEntity memberEntity) {
        Member member = new Member();
        member.setProfilePhotoUrl(memberEntity.getProfilePhotoUrl());
        member.setMemberName(memberEntity.getMemberName());
        member.setMemberEmail(memberEntity.getMemberEmail());
        member.setCoverPhotoUrl(memberEntity.getCoverPhotoUrl());
        return member;
    }

    @Override
    public Collection<MemberEntity> toEntity(Collection<Member> m) {
        return m.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public Collection<Member> toModel(Collection<MemberEntity> e) {
        return e.stream().map(this::toModel).collect(Collectors.toList());
    }
}

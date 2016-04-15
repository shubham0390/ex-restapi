package com.mmt.shubh.service.converter;

import com.mmt.shubh.database.entity.DeviceEntity;
import com.mmt.shubh.database.entity.MemberEntity;
import com.mmt.shubh.rest.model.DeviceDetails;
import com.mmt.shubh.rest.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


    @Autowired
    @Qualifier(value = "deviceEntityModelConverter")
    private IEntityModelConverter<DeviceEntity, DeviceDetails> deviceEntityModelConverter;

    public MemberEntity toEntity(Member member) {
        log.info("converting memberEntity model to entity ");
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUserPassword(member.getUserPassword());
        memberEntity.setProfilePhotoUrl(member.getProfilePhotoUrl());
        memberEntity.setMemberName(member.getMemberName());
        memberEntity.setMemberEmail(member.getMemberEmail());
        memberEntity.setCoverPhotoUrl(member.getCoverPhotoUrl());
        List<DeviceEntity> deviceEntities = new ArrayList<>();
        deviceEntities.add(deviceEntityModelConverter.toEntity(member.getDeviceDetailsList()));
        memberEntity.setDeviceEntities(deviceEntities);
        return memberEntity;
    }

    public Member toModel(MemberEntity memberEntity) {
        Member member = new Member();
        member.setProfilePhotoUrl(memberEntity.getProfilePhotoUrl());
        member.setMemberName(memberEntity.getMemberName());
        member.setMemberEmail(memberEntity.getMemberEmail());
        member.setCoverPhotoUrl(memberEntity.getCoverPhotoUrl());
        member.setDeviceDetailsList(deviceEntityModelConverter.toModel((List<DeviceEntity>)
                memberEntity.getDeviceEntities()).get(0));
        return member;
    }

    @Override
    public List<MemberEntity> toEntity(List<Member> m) {
        return m.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<Member> toModel(List<MemberEntity> e) {
        return e.stream().map(this::toModel).collect(Collectors.toList());
    }
}

package com.mmt.shubh.service.converter;

import com.mmt.shubh.database.entity.DeviceEntity;
import com.mmt.shubh.database.entity.UserEntity;
import com.mmt.shubh.rest.model.Device;
import com.mmt.shubh.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserEntityModelConverter implements IEntityModelConverter<UserEntity, User> {

    private final IEntityModelConverter<DeviceEntity, Device> deviceEntityModelConverter;

    @Autowired
    public UserEntityModelConverter(@Qualifier(value = "deviceEntityModelConverter") IEntityModelConverter<DeviceEntity, Device> deviceEntityModelConverter) {
        this.deviceEntityModelConverter = deviceEntityModelConverter;
    }

    @Override
    public UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setName(user.getName());
        entity.setCoverPhotoUrl(user.getCoverImageUrl());
        entity.setEmail(user.getEmail());
        entity.setProfilePhotoUrl(user.getProfileImageUrl());
        entity.setPhoneNumber(user.getPhoneNumber());
        return entity;
    }

    @Override
    public User toModel(UserEntity userEntity) {
        return null;
    }

    @Override
    public List<UserEntity> toEntity(List<User> m) {
        return m.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<User> toModel(List<UserEntity> e) {
        return e.stream().map(this::toModel).collect(Collectors.toList());
    }
}

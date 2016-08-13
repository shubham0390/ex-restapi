/*
 * Copyright (c) 2016. . The Km2Labs Project
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */

package com.km2labs.expenseview.service.converter;

import com.km2labs.expenseview.database.entity.DeviceEntity;
import com.km2labs.expenseview.database.entity.UserEntity;
import com.km2labs.expenseview.rest.model.Device;
import com.km2labs.expenseview.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
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
        user.setId(user.getId());
        entity.setName(user.getName());
        entity.setCoverPhotoUrl(user.getCoverImageUrl());
        entity.setEmail(user.getEmail());
        entity.setProfilePhotoUrl(user.getProfileImageUrl());
        entity.setPhoneNumber(user.getPhoneNumber());
        Set<DeviceEntity> entities = entity.getDeviceEntities();
        if (entities == null) {
            entities = new HashSet<>();
        }
        entities.add(deviceEntityModelConverter.toEntity(user.getDevice()));
        return entity;
    }

    @Override
    public User toModel(UserEntity user) {
        User entity = new User();
        entity.setId(String.valueOf(user.getId()));
        entity.setName(user.getName());
        entity.setCoverImageUrl(user.getCoverPhotoUrl());
        entity.setEmail(user.getEmail());
        entity.setProfileImageUrl(user.getProfilePhotoUrl());
        entity.setPhoneNumber(user.getPhoneNumber());
        return entity;
    }

    @Override
    public Collection<UserEntity> toEntity(Collection<User> m) {
        return m.stream().map(this::toEntity).collect(Collectors.toSet());
    }

    @Override
    public Collection<User> toModel(Collection<UserEntity> e) {
        return e.stream().map(this::toModel).collect(Collectors.toSet());
    }
}

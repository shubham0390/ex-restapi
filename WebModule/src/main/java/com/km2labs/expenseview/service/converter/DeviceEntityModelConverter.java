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
import com.km2labs.expenseview.rest.dto.Device;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by Subham Tyagi
 * On 6/6/2015.
 * <p>
 * TODO: Add class comments
 */
@Component
@Scope(value = "prototype")
public class DeviceEntityModelConverter implements IEntityModelConverter<DeviceEntity, Device> {

    public DeviceEntity toEntity(Device device) {
        DeviceEntity builder = new DeviceEntity();
        builder.setDeviceUUID(device.getDeviceUUID());
        builder.setGcmToken(device.getGcmToken());
        return builder;
    }

    public Device toModel(DeviceEntity deviceEntity) {
        Device device = new Device();
        device.setId(deviceEntity.getId());
        device.setDeviceUUID(deviceEntity.getDeviceUUID());
        device.setGcmToken(deviceEntity.getGcmToken());
        return device;
    }

    @Override
    public Collection<DeviceEntity> toEntity(Collection<Device> m) {
        return m.stream().map(this::toEntity).collect(Collectors.toSet());
    }

    @Override
    public Collection<Device> toModel(Collection<DeviceEntity> e) {
        return e.stream().map(this::toModel).collect(Collectors.toSet());
    }
}

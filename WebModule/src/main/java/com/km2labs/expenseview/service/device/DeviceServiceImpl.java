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

package com.km2labs.expenseview.service.device;

import com.km2labs.expenseview.database.entity.DeviceEntity;
import com.km2labs.expenseview.database.repository.device.IDeviceRepository;
import com.km2labs.expenseview.rest.model.Device;
import com.km2labs.expenseview.service.converter.IEntityModelConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.Collection;

/**
 * Created by Subham Tyagi
 * On 7/27/2015.
 * <p>
 * TODO: Add class comments
 */

@Component(value = "deviceServiceImpl")
@Slf4j
public class DeviceServiceImpl implements IDeviceService {

    private final IDeviceRepository mDeviceRepository;

    private final IEntityModelConverter<DeviceEntity, Device> mEntityModelConverter;

    @Autowired
    public DeviceServiceImpl(@Qualifier(value = "deviceSQLRepository") IDeviceRepository mDeviceRepository,
                             @Qualifier(value = "deviceEntityModelConverter") IEntityModelConverter<DeviceEntity, Device> mEntityModelConverter) {
        this.mDeviceRepository = mDeviceRepository;
        this.mEntityModelConverter = mEntityModelConverter;
    }

    @Override
    public DeviceEntity updateGCMToken(String gcmToken, String userId, String deviceId) {
        DeviceEntity deviceEntity = mDeviceRepository.getUserDeviceByiD(Long.parseLong(deviceId), "", userId);
        deviceEntity.setGcmToken(gcmToken);
        return mDeviceRepository.update(deviceEntity);
    }

    @Override
    public String deleteDevice(String details, String userId) {
        return null;
    }

    @Override
    public Device addDevice(long memberId, Device device) {
        log.debug("ENTER METHOD addDevice");
        DeviceEntity deviceEntity = null;

        try {
            deviceEntity = mDeviceRepository.getDeviceByMemberAndDeviceId(memberId, device.getDeviceUUID());
            log.info("Device already present");
        } catch (NonUniqueResultException e) {
            log.error("Should not be two device for same device UUID");
        } catch (NoResultException e) {
            log.info("No device present with given uuid" + device.getDeviceUUID());
        }

        if (deviceEntity == null) {
            log.debug("Updating device with new data for member id " + memberId);
            deviceEntity = mEntityModelConverter.toEntity(device);
            mDeviceRepository.save(deviceEntity);
            log.debug("EXIT METHOD addDevice");
            return null;
        } else {
            log.debug("Creating new device for member id :" + memberId);
            mDeviceRepository.save(mEntityModelConverter.toEntity(device));
            log.debug("EXIT METHOD addDevice");
            return null;
        }
    }

    @Override
    public Collection<Device> getDevicesByUser(String userId) {
        return mEntityModelConverter.toModel(mDeviceRepository.findDevicesByUser(Long.parseLong(userId)));
    }

    @Override
    public Device getUserDeviceByiD(final long id, final String deviceUUID, final String userId) {
        DeviceEntity deviceEntity = mDeviceRepository.getUserDeviceByiD(id, deviceUUID, userId);
        return mEntityModelConverter.toModel(deviceEntity);
    }
}

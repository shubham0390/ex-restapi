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
import com.km2labs.expenseview.rest.dto.Device;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by STyagi on 7/27/2015.
 */
@Service
@Transactional
public interface DeviceService {

    DeviceEntity updateGCMToken(String gcmToken, String userId, String deviceId);

    String deleteDevice(String details, String userId);

    Device addDevice(long memberID, Device device);

    Collection<Device> getDevicesByUser(String memberKey);

    Device getUserDeviceByiD(long id, String deviceUUID, String userId);

    Device updateGcmToken(String gcmToken, String deviceId, String userId);
}
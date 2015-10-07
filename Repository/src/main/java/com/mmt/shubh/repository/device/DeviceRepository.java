package com.mmt.shubh.repository.device;

import com.mmt.shubh.entity.DeviceEntity;
import com.mmt.shubh.entity.MemberEntity;

/**
 * Created by Subham Tyagi
 * On 6/2/2015.
 * <p>
 * TODO: Add class comments
 */
public interface DeviceRepository {

    void createDevice(DeviceEntity deviceEntity);

    DeviceEntity findDevice(long deviceId);

    void deleteDevice(long deviceId);

    void updateDevice(DeviceEntity deviceEntity);

    MemberEntity getUser(long deviceId);

}

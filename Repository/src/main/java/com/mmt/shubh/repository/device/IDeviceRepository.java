package com.mmt.shubh.repository.device;

import com.mmt.shubh.entity.DeviceEntity;
import com.mmt.shubh.entity.MemberEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 * Created by Subham Tyagi
 * On 6/2/2015.
 * <p>
 * TODO: Add class comments
 */
@Repository
public interface IDeviceRepository {

    long createDevice(DeviceEntity deviceEntity);

    DeviceEntity findDevice(long deviceId);

    void deleteDevice(long deviceId);

    long updateDevice(DeviceEntity deviceEntity);

    MemberEntity getUser(long deviceId);

    DeviceEntity findDeviceByUUID(String deviceUUID) throws NoResultException,NonUniqueResultException ;

    DeviceEntity getDeviceByMemberAndDeviceId(long memberId, String deviceUUID) throws NoResultException,NonUniqueResultException;
}

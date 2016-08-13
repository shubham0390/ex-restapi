package com.km2labs.expenseview.database.repository.device;

import com.km2labs.expenseview.database.entity.DeviceEntity;
import com.km2labs.expenseview.database.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/2/2015.
 * <p>
 * TODO: Add class comments
 */
@Repository
public interface IDeviceRepository extends com.km2labs.expenseview.database.repository.Repository<DeviceEntity, Long> {

    UserEntity getUser(long deviceId);

    DeviceEntity findDeviceByUUID(String deviceUUID);

    List<DeviceEntity> findDevicesByUser(long userId);

    DeviceEntity getDeviceByMemberAndDeviceId(long memberId, String deviceUUID) throws NoResultException, NonUniqueResultException;
}

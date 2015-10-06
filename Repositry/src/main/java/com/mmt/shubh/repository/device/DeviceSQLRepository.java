package com.mmt.shubh.repository.device;

import com.mmt.shubh.BaseRepository;
import com.mmt.shubh.entity.DeviceEntity;
import com.mmt.shubh.entity.MemberEntity;
import com.mmt.shubh.exception.DuplicateEntityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;

/**
 * Created by Subham Tyagi
 * On 6/2/2015.
 * <p>
 * TODO: Add class comments
 */
@Repository
@Slf4j
public class DeviceSQLRepository extends BaseRepository<DeviceEntity> implements DeviceRepository {

    public DeviceSQLRepository() {
        setClazz(DeviceEntity.class);
    }

    public void createDevice(DeviceEntity deviceEntity) {
        try {
            create(deviceEntity);
        } catch (EntityExistsException e) {
            throw new DuplicateEntityException(e.getMessage());
        }
    }

    public DeviceEntity findDevice(long deviceId) {
      /*  try {
            return findByColumnNameAndLongValue(Device_.id, deviceId);
        } catch (IllegalArgumentException iae) {
            log.error("invalid query " + iae.getMessage());
        }
        throw new NoResultException("Employee not found");*/
        return null;
    }

    public void deleteDevice(long deviceId) {
        deleteById(deviceId);
    }

    public void updateDevice(DeviceEntity deviceEntity) {
        update(deviceEntity);
    }

    public MemberEntity getUser(long deviceId) {
        return null;
    }

}

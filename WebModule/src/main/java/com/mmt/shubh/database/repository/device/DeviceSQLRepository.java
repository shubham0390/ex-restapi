package com.mmt.shubh.database.repository.device;

import com.mmt.shubh.database.BaseRepository;
import com.mmt.shubh.database.entity.DeviceEntity;
import com.mmt.shubh.database.entity.DeviceEntity_;
import com.mmt.shubh.database.entity.MemberEntity;
import com.mmt.shubh.database.entity.UserEntity_;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

/**
 * Created by Subham Tyagi
 * On 6/2/2015.
 * <p>
 * TODO: Add class comments
 */
@Slf4j
@Component(value = "deviceSQLRepository")
public class DeviceSQLRepository extends BaseRepository<DeviceEntity> implements IDeviceRepository {

    public DeviceSQLRepository() {
        setClazz(DeviceEntity.class);
    }

    public long createDevice(DeviceEntity deviceEntity) {
        log.debug("ENTER METHOD createDevice");
        create(deviceEntity);
        log.debug("EXIT METHOD createDevice");
        return deviceEntity.getId();
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

    public long updateDevice(DeviceEntity deviceEntity) {
        update(deviceEntity);
        return deviceEntity.getId();
    }

    public MemberEntity getUser(long deviceId) {
        return null;
    }

    @Override
    public DeviceEntity findDeviceByUUID(String deviceUUID) throws NoResultException, NonUniqueResultException {
        return findByColumnNameAndStringValue(DeviceEntity_.deviceUUID, deviceUUID);
    }

    @Override
    public DeviceEntity getDeviceByMemberAndDeviceId(long memberId, String deviceUUID) {
        log.debug("ENTER METHOD getDeviceByMemberAndDeviceId");
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<DeviceEntity> query = builder.createQuery(DeviceEntity.class);

        Metamodel m = entityManager.getMetamodel();

        EntityType<DeviceEntity> entityType = m.entity(DeviceEntity.class);

        Root<DeviceEntity> root = query.from(entityType);

        query.where(
                builder.and(
                        builder.equal(root.get(DeviceEntity_.deviceUUID), deviceUUID),
                        builder.equal(root.get(DeviceEntity_.user).get(UserEntity_.id), memberId)
                )
        );
        DeviceEntity deviceEntity = entityManager.createQuery(query).getSingleResult();
        log.debug("ENTER METHOD getDeviceByMemberAndDeviceId");
        return deviceEntity;
    }

}

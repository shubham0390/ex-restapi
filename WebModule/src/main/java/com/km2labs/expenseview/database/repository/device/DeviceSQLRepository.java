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

package com.km2labs.expenseview.database.repository.device;

import com.km2labs.expenseview.database.entity.DeviceEntity;
import com.km2labs.expenseview.database.entity.UserEntity;
import com.km2labs.expenseview.database.repository.BaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.List;

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

    @Override
    public UserEntity getUser(long deviceId) {
        return null;
    }

    @Override
    public DeviceEntity findDeviceByUUID(String deviceUUID) throws NoResultException, NonUniqueResultException {
        return findByColumnNameAndValue(DeviceEntity_.deviceUUID, deviceUUID);
    }

    @Override
    public List<DeviceEntity> findDevicesByUser(final long userId) {
        CriteriaBuilder builder = mEntityManager.getCriteriaBuilder();
        CriteriaQuery<DeviceEntity> query = builder.createQuery(DeviceEntity.class);
        Metamodel metamodel = mEntityManager.getMetamodel();
        EntityType<DeviceEntity> entityType = metamodel.entity(DeviceEntity.class);
        Root<DeviceEntity> root = query.from(entityType);
        query.where(builder.equal(root.get(DeviceEntity_.user).get(UserEntity_.id), userId));
        return mEntityManager.createQuery(query).getResultList();
    }

    @Override
    public DeviceEntity getDeviceByMemberAndDeviceId(long memberId, String deviceUUID) {
        log.debug("ENTER METHOD getDeviceByMemberAndDeviceId");
        CriteriaBuilder builder = mEntityManager.getCriteriaBuilder();
        CriteriaQuery<DeviceEntity> query = builder.createQuery(DeviceEntity.class);
        Metamodel m = mEntityManager.getMetamodel();
        EntityType<DeviceEntity> entityType = m.entity(DeviceEntity.class);
        Root<DeviceEntity> root = query.from(entityType);
        query.where(
                builder.and(
                        builder.equal(root.get(DeviceEntity_.deviceUUID), deviceUUID),
                        builder.equal(root.get(DeviceEntity_.user).get(UserEntity_.id), memberId)
                )
        );

        DeviceEntity deviceEntity = mEntityManager.createQuery(query).getSingleResult();
        log.debug("ENTER METHOD getDeviceByMemberAndDeviceId");
        return deviceEntity;
    }

    @Override
    public DeviceEntity save(DeviceEntity entity) {
        log.debug("ENTER METHOD createDevice");
        create(entity);
        log.debug("EXIT METHOD createDevice");
        return entity;
    }

    @Override
    public DeviceEntity findOne(Long var1) {
        return super.findOne(var1);
    }

    @Override
    public boolean exists(Long var1) {
        DeviceEntity deviceEntity = super.findOne(var1);
        return deviceEntity != null;
    }

    @Override
    public List<DeviceEntity> findAll(List var1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long var1) {
        super.deleteById(var1);
    }

    @Override
    public void delete(Iterable<Long> deviceIds) {
        deviceIds.forEach(super::deleteById);
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }
}

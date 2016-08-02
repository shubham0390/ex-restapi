package com.mmt.shubh.service.device;

import com.mmt.shubh.database.entity.DeviceEntity;
import com.mmt.shubh.database.repository.device.IDeviceRepository;
import com.mmt.shubh.database.repository.member.IMemberRepository;
import com.mmt.shubh.rest.model.Device;
import com.mmt.shubh.service.converter.IEntityModelConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 * Created by Subham Tyagi
 * On 7/27/2015.
 * <p>
 * TODO: Add class comments
 */

@Component(value = "deviceServiceImpl")
@Slf4j
public class DeviceServiceImpl implements IDeviceService {

    @Qualifier(value = "memberSQLRepository")
    @Autowired
    IMemberRepository mMemberRepository;

    @Qualifier(value = "deviceSQLRepository")
    @Autowired
    IDeviceRepository mDeviceRepository;

    @Qualifier(value = "deviceEntityModelConverter")
    @Autowired
    IEntityModelConverter<DeviceEntity, Device> mEntityModelConverter;

    @Override
    public String updateGCMToken(String gcmToken, String emailId) {
        return null;
    }

    @Override
    public Device updateDevice(Device device, String emailId) {
        return null;
    }

    @Override
    public String deleteDevice(String details, String emailId) {
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
            long id = mDeviceRepository.createDevice(deviceEntity);
            log.debug("EXIT METHOD addDevice");
            return null;
        } else {
            log.debug("Creating new device for member id :" + memberId);
            long id = mDeviceRepository.updateDevice(mEntityModelConverter.toEntity(device));
            log.debug("EXIT METHOD addDevice");
            return null;
        }
    }

    @Override
    public Device getDeviceByMemberKey(long memberKey) {
        return null;
    }
}

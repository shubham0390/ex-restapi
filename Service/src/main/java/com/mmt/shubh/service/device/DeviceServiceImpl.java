package com.mmt.shubh.service.device;

import com.mmt.shubh.entity.DeviceEntity;
import com.mmt.shubh.entity.MemberEntity;
import com.mmt.shubh.repository.device.IDeviceRepository;
import com.mmt.shubh.repository.member.IMemberRepository;
import com.mmt.shubh.rest.model.DeviceDetails;
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

@Component
@Slf4j

public class DeviceServiceImpl implements IDeviceService {

    @Qualifier("memberRepositoryImpl")
    @Autowired
    IMemberRepository mMemberRepository;

    @Qualifier("deviceSQLRepository")
    @Autowired
    IDeviceRepository mDeviceRepository;

    @Qualifier("deviceEntityModelConverter")
    @Autowired
    IEntityModelConverter<DeviceEntity, DeviceDetails> mEntityModelConverter;

    @Override
    public String updateGCMToken(String gcmToken, String emailId) {
        return null;
    }

    @Override
    public DeviceDetails updateDevice(DeviceDetails deviceDetails, String emailId) {
        return null;
    }

    @Override
    public String deleteDevice(String details, String emailId) {
        return null;
    }

    @Override
    public long addDevice(long memberId, DeviceDetails deviceDetails) {
        log.debug("ENTER METHOD addDevice");
        DeviceEntity deviceEntity = null;

        try {
            deviceEntity = mDeviceRepository.getDeviceByMemberAndDeviceId(memberId, deviceDetails.getDeviceUUID());
            log.info("Device already present");
        } catch (NonUniqueResultException e) {
            log.error("Should not be two device for same device UUID");
        } catch (NoResultException e) {
            log.info("No device present with given uuid" + deviceDetails.getDeviceUUID());
        }

        if (deviceEntity == null) {
            log.debug("Updating device with new data for member id " + memberId);
            MemberEntity memberEntity = mMemberRepository.getMemberById(memberId);
            deviceEntity = mEntityModelConverter.toEntity(deviceDetails);
            deviceEntity.setMember(memberEntity);
            long id = mDeviceRepository.createDevice(deviceEntity);
            log.debug("EXIT METHOD addDevice");
            return id;
        } else {
            log.debug("Creating new device for member id :" + memberId);
            long id = mDeviceRepository.updateDevice(mEntityModelConverter.toEntity(deviceDetails));
            log.debug("EXIT METHOD addDevice");
            return id;
        }
    }
}

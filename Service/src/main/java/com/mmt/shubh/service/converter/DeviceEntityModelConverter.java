package com.mmt.shubh.service.converter;

import com.mmt.shubh.entity.DeviceEntity;
import com.mmt.shubh.rest.model.DeviceDetails;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/6/2015.
 * <p>
 * TODO: Add class comments
 */
@Component
@Scope(value = "prototype")
public class DeviceEntityModelConverter implements IEntityModelConverter<DeviceEntity, DeviceDetails> {

    public DeviceEntity toEntity(DeviceDetails deviceDetails) {
        DeviceEntity builder = new DeviceEntity();
        builder.setDeviceUUID(deviceDetails.getDeviceUUID());
        builder.setRegistrationToken(deviceDetails.getGcmToken());
        return builder;
    }

    public DeviceDetails toModel(DeviceEntity deviceEntity) {
        DeviceDetails builder = new DeviceDetails();
        builder.setDeviceUUID(deviceEntity.getDeviceUUID());
        builder.setGcmToken(deviceEntity.getRegistrationToken());
        return builder;
    }

    @Override
    public List<DeviceEntity> toEntity(List<DeviceDetails> m) {
        return null;
    }

    @Override
    public List<DeviceDetails> toModel(List<DeviceEntity> e) {
        return null;
    }
}

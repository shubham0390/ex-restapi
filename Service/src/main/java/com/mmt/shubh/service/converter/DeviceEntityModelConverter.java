package com.mmt.shubh.service.converter;

import com.mmt.shubh.entity.DeviceEntity;
import com.mmt.shubh.rest.model.DeviceDetails;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Subham Tyagi
 * On 6/6/2015.
 * <p>
 * TODO: Add class comments
 */
@Component
@Scope(value = "prototype")
public class DeviceEntityModelConverter implements EntityModelConverter<DeviceEntity, DeviceDetails> {

    public DeviceEntity toEntity(DeviceDetails deviceDetails) {
        DeviceEntity.Builder builder = new DeviceEntity.Builder();
        builder.setDeviceUUID(deviceDetails.getDeviceUUID());
        builder.setRegistrationToken(deviceDetails.getGcmToken());
        return builder.build();
    }

    public DeviceDetails toModel(DeviceEntity deviceEntity) {
        DeviceDetails.Builder builder = new DeviceDetails.Builder();
        builder.setDeviceUUID(deviceEntity.getDeviceUUID());
        builder.setGcmToken(deviceEntity.getRegistrationToken());
        return builder.build();
    }
}

package com.mmt.shubh.service.converter;

import com.mmt.shubh.database.entity.DeviceEntity;
import com.mmt.shubh.rest.model.Device;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Subham Tyagi
 * On 6/6/2015.
 * <p>
 * TODO: Add class comments
 */
@Component
@Scope(value = "prototype")
public class DeviceEntityModelConverter implements IEntityModelConverter<DeviceEntity, Device> {

    public DeviceEntity toEntity(Device device) {
        DeviceEntity builder = new DeviceEntity();
        builder.setDeviceUUID(device.getDeviceUUID());
        builder.setRegistrationToken(device.getGcmToken());
        return builder;
    }

    public Device toModel(DeviceEntity deviceEntity) {
        Device builder = new Device();
        builder.setDeviceUUID(deviceEntity.getDeviceUUID());
        builder.setGcmToken(deviceEntity.getRegistrationToken());
        return builder;
    }

    @Override
    public List<DeviceEntity> toEntity(List<Device> m) {
        return m.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<Device> toModel(List<DeviceEntity> e) {
        return e.stream().map(this::toModel).collect(Collectors.toList());
    }
}

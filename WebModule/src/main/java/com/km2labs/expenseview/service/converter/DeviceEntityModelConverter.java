package com.km2labs.expenseview.service.converter;

import com.km2labs.expenseview.database.entity.DeviceEntity;
import com.km2labs.expenseview.rest.model.Device;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
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
        builder.setGcmToken(device.getGcmToken());
        return builder;
    }

    public Device toModel(DeviceEntity deviceEntity) {
        Device device = new Device();
        device.setId(deviceEntity.getId());
        device.setDeviceUUID(deviceEntity.getDeviceUUID());
        device.setGcmToken(deviceEntity.getGcmToken());
        return device;
    }

    @Override
    public Collection<DeviceEntity> toEntity(Collection<Device> m) {
        return m.stream().map(this::toEntity).collect(Collectors.toSet());
    }

    @Override
    public Collection<Device> toModel(Collection<DeviceEntity> e) {
        return e.stream().map(this::toModel).collect(Collectors.toSet());
    }
}

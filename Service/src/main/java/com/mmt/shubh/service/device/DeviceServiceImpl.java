package com.mmt.shubh.service.device;

import com.mmt.shubh.rest.model.DeviceDetails;
import org.springframework.stereotype.Component;

/**
 * Created by STyagi on 7/27/2015.
 */
@Component
public class DeviceServiceImpl implements IDeviceService {
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
    public DeviceDetails addDevice(String emailId, DeviceDetails deviceDetails) {
        return null;
    }
}

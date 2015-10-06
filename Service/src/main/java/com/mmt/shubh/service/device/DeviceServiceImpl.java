package com.mmt.shubh.service.device;

import com.mmt.shubh.rest.model.DeviceDetails;
import com.mmt.shubh.rest.response.ServiceResponse;
import org.springframework.stereotype.Component;

/**
 * Created by STyagi on 7/27/2015.
 */
@Component
public class DeviceServiceImpl implements IDeviceService {
    @Override
    public ServiceResponse updateGCMToken(DeviceDetails deviceDetails, String emailId) {
        return null;
    }

    @Override
    public ServiceResponse updateDevice(DeviceDetails deviceDetails, String emailId) {
        return null;
    }

    @Override
    public ServiceResponse deleteDevice(String details, String emailId) {
        return null;
    }
}

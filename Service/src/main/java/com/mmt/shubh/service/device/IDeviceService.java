package com.mmt.shubh.service.device;

import com.mmt.shubh.rest.model.DeviceDetails;
import com.mmt.shubh.rest.response.ServiceResponse;
import org.springframework.stereotype.Service;

/**
 * Created by STyagi on 7/27/2015.
 */
@Service
public interface IDeviceService {
    
    ServiceResponse updateGCMToken(DeviceDetails deviceDetails, String emailId);
    ServiceResponse updateDevice(DeviceDetails deviceDetails, String emailId);

    ServiceResponse deleteDevice(String details, String emailId);
}

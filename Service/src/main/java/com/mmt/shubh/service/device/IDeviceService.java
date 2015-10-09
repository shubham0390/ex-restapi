package com.mmt.shubh.service.device;

import com.mmt.shubh.rest.model.DeviceDetails;
import com.mmt.shubh.rest.response.ServiceResponse;
import org.springframework.stereotype.Service;

/**
 * Created by STyagi on 7/27/2015.
 */
@Service
public interface IDeviceService {
    
    String updateGCMToken(String gcmToken, String emailId);
    DeviceDetails updateDevice(DeviceDetails deviceDetails, String emailId);

    String deleteDevice(String details, String emailId);

    DeviceDetails addDevice(String emailId, DeviceDetails deviceDetails);
}

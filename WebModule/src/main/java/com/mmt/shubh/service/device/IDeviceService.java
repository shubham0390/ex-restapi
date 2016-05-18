package com.mmt.shubh.service.device;

import com.mmt.shubh.rest.model.DeviceDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by STyagi on 7/27/2015.
 */
@Service
@Transactional
public interface IDeviceService {
    
    String updateGCMToken(String gcmToken, String emailId);
    DeviceDetails updateDevice(DeviceDetails deviceDetails, String emailId);

    String deleteDevice(String details, String emailId);

    DeviceDetails addDevice(long memberID, DeviceDetails deviceDetails);

    DeviceDetails getDeviceByMemberKey(long memberKey);
}

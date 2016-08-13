package com.km2labs.expenseview.service.device;

import com.km2labs.expenseview.rest.model.Device;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by STyagi on 7/27/2015.
 */
@Service
@Transactional
public interface IDeviceService {

    String updateGCMToken(String gcmToken, long memberId, String deviceUUId);

    Device updateDevice(Device device, String emailId);

    String deleteDevice(String details, String emailId);

    Device addDevice(long memberID, Device device);

    Device getDeviceByMemberKey(long memberKey);
}

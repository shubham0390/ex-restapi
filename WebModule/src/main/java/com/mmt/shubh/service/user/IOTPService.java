package com.mmt.shubh.service.user;

import com.mmt.shubh.database.entity.DeviceEntity;
import com.mmt.shubh.database.entity.UserEntity;
import org.springframework.stereotype.Service;


@Service
public interface IOTPService {
    void generateOtp(UserEntity userEntity, DeviceEntity deviceEntity);
}

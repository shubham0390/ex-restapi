package com.km2labs.expenseview.service.user;

import com.km2labs.expenseview.database.entity.DeviceEntity;
import com.km2labs.expenseview.database.entity.UserEntity;
import org.springframework.stereotype.Service;


@Service
public interface IOTPService {
    void generateOtp(UserEntity userEntity, DeviceEntity deviceEntity);
}

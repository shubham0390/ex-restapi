package com.km2labs.expenseview.database.repository.otp;

import com.km2labs.expenseview.database.entity.UserOTPEntity;
import com.km2labs.expenseview.database.repository.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by suze on 31/07/16.
 */
@Service
@Transactional
public interface IOTPRepository extends Repository<UserOTPEntity, Long> {
    UserOTPEntity findOne(long mobileNo, long deviceId);
}

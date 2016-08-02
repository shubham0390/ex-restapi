package com.mmt.shubh.database.repository;

import com.mmt.shubh.database.entity.UserOTPEntity;
import org.springframework.stereotype.Service;

/**
 * Created by suze on 31/07/16.
 */
@Service
public interface IOTPRepository extends Repository<UserOTPEntity, Long> {
}

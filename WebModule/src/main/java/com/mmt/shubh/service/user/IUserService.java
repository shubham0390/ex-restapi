package com.mmt.shubh.service.user;

import com.mmt.shubh.rest.model.Device;
import com.mmt.shubh.rest.model.User;
import org.springframework.stereotype.Service;

/**
 * Created by suze on 30/07/16.
 */
@Service
public interface IUserService {
    User signup(User user, Device device);
}

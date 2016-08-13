package com.km2labs.expenseview.service.user;

import com.km2labs.expenseview.rest.model.Device;
import com.km2labs.expenseview.rest.model.User;
import org.springframework.stereotype.Service;

/**
 * Created by suze on 30/07/16.
 */
@Service
public interface IUserService {
    User signup(String loginType, String authenticationToken, User user);

    long login(String loginType, String authenticationToken, String clintId, Device device);
}

package com.km2labs.expenseview.service.auth;

import com.km2labs.expenseview.common.authproviders.AuthProvider;
import com.km2labs.expenseview.common.authproviders.AuthProviderType;
import com.km2labs.expenseview.exception.AuthenticationException;
import com.km2labs.expenseview.exception.DataException;
import com.km2labs.expenseview.exception.ErrorCodes;
import com.km2labs.expenseview.rest.dto.Device;
import com.km2labs.expenseview.rest.dto.User;
import com.km2labs.expenseview.rest.dto.auth.LoginRequestDto;
import com.km2labs.expenseview.rest.dto.auth.SignupRequestDto;
import com.km2labs.expenseview.service.device.DeviceService;
import com.km2labs.expenseview.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by subhamtyagi on 06/01/17.
 */
@Service
public class AuthService {

    private final ApplicationContext mApplicationContext;

    private final UserService mUserService;

    private final DeviceService mDeviceService;

    @Autowired
    public AuthService(ApplicationContext mApplicationContext, @Qualifier(value = "userService") UserService mUserService,
                       @Qualifier(value = "deviceServiceImpl") DeviceService mDeviceService) {
        this.mApplicationContext = mApplicationContext;
        this.mUserService = mUserService;
        this.mDeviceService = mDeviceService;
    }


    public User register(SignupRequestDto signupRequestDto) {

        AuthProvider authProvider = signupRequestDto.getAuthProvider();
        AuthValidator authValidator = getAuthValidator(authProvider.getAuthProviderType());

        boolean result = authValidator.validate(authProvider);

        if (result) {
            throw new AuthenticationException("Authentication Failed");
        }

        User user = mUserService.createUser(signupRequestDto.getUser());

        mDeviceService.addDevice(user.getId(), signupRequestDto.getDevice());

        return user;
    }

    public User login(LoginRequestDto loginRequestDto) {
        List<DataException.DataError> errors = new ArrayList<>();

        AuthProvider authProvider = loginRequestDto.getAuthProvider();
        AuthValidator authValidator = getAuthValidator(authProvider.getAuthProviderType());

        boolean result = authValidator.validate(authProvider);

        if (result) {
            throw new AuthenticationException("Authentication Failed");
        }
        mUserService.validateUserInternal();
        return null;
    }

    private AuthValidator getAuthValidator(AuthProviderType authProviderType) {
        return (AuthValidator) mApplicationContext.getAutowireCapableBeanFactory().getBean(authProviderType.getName());
    }
}

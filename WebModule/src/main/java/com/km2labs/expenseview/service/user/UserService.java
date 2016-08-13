package com.km2labs.expenseview.service.user;

import com.km2labs.expenseview.database.entity.DeviceEntity;
import com.km2labs.expenseview.database.entity.UserEntity;
import com.km2labs.expenseview.database.repository.user.IUserRepository;
import com.km2labs.expenseview.exception.*;
import com.km2labs.expenseview.exception.DataException.DataError;
import com.km2labs.expenseview.rest.model.Device;
import com.km2labs.expenseview.rest.model.User;
import com.km2labs.expenseview.service.converter.IEntityModelConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Component
@Transactional
@Slf4j
public class UserService implements IUserService {

    private final IUserRepository mUserRepository;


    private final IEntityModelConverter<UserEntity, User> mUserEntityModelConverter;

    private final IEntityModelConverter<DeviceEntity, Device> mDeviceEntityModelConverter;

    private IOTPService mOTPService;

    @Autowired
    public UserService(@Qualifier(value = "userRepository") IUserRepository userRepository,
                       @Qualifier(value = "OTPService") IOTPService optService,
                       @Qualifier(value = "userEntityModelConverter") IEntityModelConverter<UserEntity, User> userEntityModelConverter,
                       @Qualifier(value = "deviceEntityModelConverter") IEntityModelConverter<DeviceEntity, Device> deviceEntityModelConverter) {

        this.mUserRepository = userRepository;
        this.mUserEntityModelConverter = userEntityModelConverter;
        this.mOTPService = optService;
        this.mDeviceEntityModelConverter = deviceEntityModelConverter;
    }

    @Override
    public User signup(String loginType, String authenticationToken, User user) {

        List<DataError> errors = new ArrayList<>();

        if (StringUtils.isEmpty(authenticationToken) || StringUtils.isEmpty(loginType)) {
            throw new AuthenticationException("Invalid authentication token");
        }

        if (user == null) {
            errors.add(new DataError(ErrorCodes.MISSING_USER, "User can not be empty"));
            throw new DataException(errors);
        }

        Device device = user.getDevice();
        if (device == null) {
            errors.add(new DataError(ErrorCodes.MISSING_DEVICE, "Device can not be empty"));
            throw new DataException(errors);
        }

        /*if (!TokenValidatorFactory.getTokenValidaor(loginType).isValid(user, authenticationToken)) {
            throw new AuthenticationException("Invalid authentication token");
        }*/

        errors.addAll(validateUser(user));

        if (errors.size() > 0) {
            throw new DataException(errors);
        }

        UserEntity dbUserEntity = null;
        try {
            dbUserEntity = mUserRepository.findUserWithEmailOrPhone(user.getEmail(), user.getPhoneNumber());
        } catch (EmptyResultDataAccessException e) {
            log.info("No user present for following details phone no -:" + user.getPhoneNumber() + "email -:" + user.getEmail());
        }

        if (dbUserEntity != null) {
            throw new DuplicateEntityException("Duplicate User");
        }

        DeviceEntity deviceEntity = mDeviceEntityModelConverter.toEntity(device);
        UserEntity userEntity = mUserEntityModelConverter.toEntity(user);

        Set<DeviceEntity> deviceEntities = userEntity.getDeviceEntities();
        if (deviceEntities == null) {
            deviceEntities = new HashSet<>();
            userEntity.setDeviceEntities(deviceEntities);
        }
        deviceEntity.setUser(userEntity);
        deviceEntities.add(deviceEntity);

        deviceEntities.add(deviceEntity);
        userEntity = mUserRepository.save(userEntity);

        //sending otp to device
        mOTPService.generateOtp(userEntity, deviceEntity);

        return mUserEntityModelConverter.toModel(userEntity);
    }

    @Override
    public long login(String loginType, String authenticationToken, String mobileNo, Device device) {

        /*if (!TokenValidatorFactory.getTokenValidaor(loginType).isValid(authenticationToken, clientId)) {
            throw new AuthenticationException("Invalid authentication token");
        }*/

        UserEntity userEntity = null;
        try {
            userEntity = mUserRepository.findUserByPhoneNo(mobileNo);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotPresentException("User is not present. Please signup first");
        }

        //Check if device already present if not present than create new device.
        Set<DeviceEntity> deviceEntities = userEntity.getDeviceEntities();
        Optional<DeviceEntity> optionalDE = deviceEntities.stream().filter(deviceEntity1 -> deviceEntity1.getDeviceUUID().equals(device.getDeviceUUID())).findFirst();
        DeviceEntity deviceEntity = optionalDE.isPresent() ? optionalDE.get() : null;

        if (deviceEntity == null) {
            deviceEntity = mDeviceEntityModelConverter.toEntity(device);
            deviceEntity.setUser(userEntity);
            deviceEntities.add(deviceEntity);
            mUserRepository.update(userEntity);
        }

        mOTPService.generateOtp(userEntity, deviceEntity);
        return userEntity.getId();
    }

    private List<DataException.DataError> validateUser(User user) {
        List<DataError> errors = new ArrayList<>();

        if (StringUtils.isEmpty(user.getEmail()))
            errors.add(new DataError(ErrorCodes.MISSING_EMAIL_ADDRESS, "Email Address can't be  Empty"));

        if (StringUtils.isEmpty(user.getPhoneNumber()))
            errors.add(new DataError(ErrorCodes.MISSING_PHONE_NUMBER, "Phone number can't be Empty"));

        Device device = user.getDevice();

        if (device == null) {
            errors.add(new DataError(ErrorCodes.MISSING_DEVICE, "Device can't be empty"));
            return errors;
        }
        if (StringUtils.isEmpty(device.getDeviceUUID())) {
            errors.add(new DataError(ErrorCodes.MISSING_DEVICE_UUID, "Device UUID can't be empty"));
        }
        return errors;

    }


}

package com.mmt.shubh.service.user;

import com.mmt.shubh.database.entity.DeviceEntity;
import com.mmt.shubh.database.entity.UserEntity;
import com.mmt.shubh.database.repository.IUserRepository;
import com.mmt.shubh.rest.model.Device;
import com.mmt.shubh.rest.model.User;
import com.mmt.shubh.service.converter.IEntityModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserService implements IUserService {

    private final IUserRepository mUserRepository;

    private final IEntityModelConverter<UserEntity, User> mUserEntityModelConverter;

    private final IEntityModelConverter<DeviceEntity, Device> mDeviceEntityModelConverter;

    private IOTPService mOTPService;

    @Autowired
    public UserService(@Qualifier(value = "userRepository") IUserRepository mUserRepository,
                       @Qualifier(value = "userEntityModelConverter") IEntityModelConverter<UserEntity, User> mUserEntityModelConverter
            , @Qualifier(value = "OTPService") IOTPService optService, @Qualifier(value = "deviceEntityModelConverter") IEntityModelConverter<DeviceEntity, Device> mDeviceEntityModelConverter) {
        this.mUserRepository = mUserRepository;
        this.mUserEntityModelConverter = mUserEntityModelConverter;
        this.mOTPService = optService;
        this.mDeviceEntityModelConverter = mDeviceEntityModelConverter;
    }

    @Override
    public User signup(User user, Device device) {
        DeviceEntity deviceEntity = mDeviceEntityModelConverter.toEntity(device);
        UserEntity userEntity = mUserEntityModelConverter.toEntity(user);

        Set<DeviceEntity> deviceEntities = userEntity.getDeviceEntities();
        if (deviceEntities == null) {
            deviceEntities = new HashSet<>();
            userEntity.setDeviceEntities(deviceEntities);
        }

        deviceEntities.add(deviceEntity);
        userEntity = mUserRepository.save(userEntity);
        //sending otp to device
        mOTPService.generateOtp(userEntity, deviceEntity);

        return mUserEntityModelConverter.toModel(userEntity);
    }
}

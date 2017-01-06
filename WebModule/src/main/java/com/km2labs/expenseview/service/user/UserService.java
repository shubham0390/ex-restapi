/*
 * Copyright (c) 2016. . The Km2Labs Project
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */

package com.km2labs.expenseview.service.user;

import com.km2labs.expenseview.database.entity.UserEntity;
import com.km2labs.expenseview.database.repository.user.IUserRepository;
import com.km2labs.expenseview.exception.*;
import com.km2labs.expenseview.exception.DataException.DataError;
import com.km2labs.expenseview.rest.dto.User;
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


    @Autowired
    public UserService(@Qualifier(value = "userRepository") IUserRepository userRepository,
                       @Qualifier(value = "userEntityModelConverter") IEntityModelConverter<UserEntity, User> userEntityModelConverter) {
        this.mUserRepository = userRepository;
        this.mUserEntityModelConverter = userEntityModelConverter;
    }

    @Override
    public User createUser(User user) {

        List<DataError> errors = new ArrayList<>();

        if (user == null) {
            errors.add(new DataError(ErrorCodes.MISSING_USER, "User can not be empty"));
            throw new DataException(errors);
        }

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

        UserEntity userEntity = mUserEntityModelConverter.toEntity(user);

        userEntity = mUserRepository.save(userEntity);

        return mUserEntityModelConverter.toModel(userEntity);
    }

    @Override
    public void validateUserInternal() {

    }

    private List<DataException.DataError> validateUser(User user) {
        List<DataError> errors = new ArrayList<>();

        if (StringUtils.isEmpty(user.getName()))
            errors.add(new DataError(ErrorCodes.MISSING_USER_NAME, "User name can't be  Empty"));

        if (StringUtils.isEmpty(user.getPhoneNumber()))
            errors.add(new DataError(ErrorCodes.MISSING_PHONE_NUMBER, "Phone number can't be Empty"));

        return errors;
    }
}

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

package com.km2labs.expenseview.database.repository.otp;

import com.km2labs.expenseview.database.entity.UserOTPEntity;
import com.km2labs.expenseview.database.repository.BaseRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OTPSQLRepository extends BaseRepository<UserOTPEntity> implements IOTPRepository {

    @Override
    public UserOTPEntity save(UserOTPEntity var1) {
        create(var1);
        return var1;
    }

    @Override
    public UserOTPEntity findOne(Long var1) {
        return null;
    }

    @Override
    public boolean exists(Long var1) {
        return false;
    }

    @Override
    public List<UserOTPEntity> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<UserOTPEntity> findAll(List<Long> var1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long var1) {

    }

    @Override
    public void delete(final Iterable<Long> var1) {

    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserOTPEntity findOne(final long mobileNo, final long deviceId) {
        return null;
    }
}

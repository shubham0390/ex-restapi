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

package com.km2labs.expenseview.database.repository.user;

import com.km2labs.expenseview.database.entity.UserEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface IUserRepository extends com.km2labs.expenseview.database.repository.Repository<UserEntity, Long> {

    UserEntity findUserWithEmailOrPhone(String email, String phoneNumber);

    UserEntity findUserByPhoneNo(String mobileNo);
}

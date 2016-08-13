package com.km2labs.expenseview.database.repository.user;

import com.km2labs.expenseview.database.entity.UserEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface IUserRepository extends com.km2labs.expenseview.database.repository.Repository<UserEntity, Long> {

    UserEntity findUserWithEmailOrPhone(String email, String phoneNumber);

    UserEntity findUserByPhoneNo(String mobileNo);
}

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

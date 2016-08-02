package com.mmt.shubh.database.repository;

import com.mmt.shubh.database.BaseRepository;
import com.mmt.shubh.database.entity.UserOTPEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by suze on 31/07/16.
 */
@Component
public class OTPRepository extends BaseRepository<UserOTPEntity> implements IOTPRepository {

    @Override
    public UserOTPEntity save(UserOTPEntity var1) {
        create(var1);
        return var1;
    }

    @Override
    public <S extends UserOTPEntity> Iterable<S> save(Iterable<S> var1) {
        return null;
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
        return null;
    }

    @Override
    public List<UserOTPEntity> findAll(List<Long> var1) {
        return null;
    }

    @Override
    public void delete(Long var1) {

    }

    @Override
    public void delete(Iterable<? extends Long> var1) {

    }

    @Override
    public void deleteAll() {

    }
}

package com.mmt.shubh.database.repository;

import com.mmt.shubh.database.BaseRepository;
import com.mmt.shubh.database.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepository extends BaseRepository<UserEntity> implements IUserRepository {

    @Override
    public UserEntity save(UserEntity var1) {
        create(var1);
        return var1;
    }

    @Override
    public <S extends UserEntity> Iterable<S> save(Iterable<S> var1) {
        return null;
    }

    @Override
    public UserEntity findOne(Long var1) {
        return findOne(var1);
    }

    @Override
    public boolean exists(Long var1) {
        return false;
    }

    @Override
    public List<UserEntity> findAll() {
        return findAll();
    }

    @Override
    public List<UserEntity> findAll(List<Long> var1) {
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

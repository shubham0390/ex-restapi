package com.mmt.shubh.database.repository;

import com.mmt.shubh.database.entity.UserEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface IUserRepository extends com.mmt.shubh.database.repository.Repository<UserEntity, Long> {
}

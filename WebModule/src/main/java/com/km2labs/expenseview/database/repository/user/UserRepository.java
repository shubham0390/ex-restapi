package com.km2labs.expenseview.database.repository.user;

import com.km2labs.expenseview.database.entity.UserEntity;
import com.km2labs.expenseview.database.entity.UserEntity_;
import com.km2labs.expenseview.database.repository.BaseRepository;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository extends BaseRepository<UserEntity> implements IUserRepository {

    public UserRepository() {
        setClazz(UserEntity.class);
    }

    @Override
    public UserEntity save(UserEntity var1) {
        create(var1);
        return var1;
    }

    @Override
    public UserEntity findOne(Long var1) {
        return super.findOne(var1);
    }

    @Override
    public boolean exists(Long var1) {
        UserEntity userEntity = super.findOne(var1);
        return userEntity != null;
    }

    @Override
    public List<UserEntity> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<UserEntity> findAll(List<Long> var1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long var1) {
        super.deleteById(var1);
    }

    @Override
    public void delete(final Iterable<Long> var1) {
        throw new UnsupportedOperationException();
    }


    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserEntity findUserWithEmailOrPhone(String email, String phoneNumber) {
        CriteriaBuilder cb = mEntityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(clazz);

        Metamodel m = mEntityManager.getMetamodel();
        EntityType<UserEntity> entityType = m.entity(clazz);
        Root<UserEntity> c = cq.from(entityType);
        cq.select(c);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(c.get(UserEntity_.email), email));
        predicates.add(cb.equal(c.get(UserEntity_.phoneNumber), phoneNumber));
        Predicate predicate = cb.and(predicates.toArray(new Predicate[]{}));
        cq.where(predicate);
        return mEntityManager.createQuery(cq).getSingleResult();
    }

    @Override
    public UserEntity findUserByPhoneNo(final String mobileNo) {
        return findByColumnNameAndValue(UserEntity_.phoneNumber, mobileNo);
    }
}
